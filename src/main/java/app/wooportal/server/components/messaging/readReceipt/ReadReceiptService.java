package app.wooportal.server.components.messaging.readReceipt;

import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AuthenticationService;

@Service
public class ReadReceiptService extends DataService<ReadReceiptEntity, ReadReceiptPredicateBuilder> {
  
  private final PushService pushService;
  private final UserService userService;
  private final AuthenticationService authService;
  private final ParticipantService participantService;

  public ReadReceiptService(DataRepository<ReadReceiptEntity> repo,
      ReadReceiptPredicateBuilder predicate,
      PushService pushService,
      UserService userService,
      AuthenticationService authService,
      ParticipantService participantService) {
    super(repo, predicate);

    this.pushService = pushService;
    this.userService = userService;  
    this.authService = authService;
    this.participantService = participantService;
  }
  
  @Override
  public Optional<ReadReceiptEntity> getExisting(ReadReceiptEntity entity) {
    return entity.getParticipant() == null || entity.getMessage() == null
        ? Optional.empty()
        : getByParticipantAndMessage(
            entity.getParticipant().getId(),
            entity.getMessage().getId());
  }
  
  private Optional<ReadReceiptEntity> getByParticipantAndMessage(String participantId,String messageId) {
    return repo.findOne(predicate.withParticipantAndMessage(participantId, messageId));
  }

  public Optional<ReadReceiptEntity> getByUserAndMessage(String messageId) {
    var currentUser = this.authService.getAuthenticatedUser();

    if (currentUser.isPresent() && messageId != null) {

      var result = readAll(query()
          .and(predicate.withUser(currentUser.get().getId()).and(predicate.withMessage(messageId)))
          .setLimit(1));

      return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }
    return Optional.empty();
  }
  
  @Override
  protected void preSave(ReadReceiptEntity entity, ReadReceiptEntity newEntity, JsonNode context) {
    var currentUser = this.authService.getAuthenticatedUser();
    
    if (currentUser.isPresent() && newEntity.getMessage() != null) {
    
      var participant = participantService.readAll(participantService.query()
          .and(participantService.getPredicate().withUser(currentUser.get().getId())
              .and(participantService.getPredicate().withChatMessage(newEntity.getMessage().getId())))
          .setLimit(1)).getList().get(0);
      newEntity.setParticipant(participant);   
      }
      else {throw new BadParamsException("chat or user does not exist");
      
      }
      setContext("user", context);
    }

  @Override
  protected void postSave(ReadReceiptEntity saved, ReadReceiptEntity newEntity, JsonNode context) {
    var message = new MessageDto(null, null,
        Map.of(NotificationType.chat.toString(), saved.getId(),
            "ParticipantId", saved.getParticipant().getId(),
            "MessageId", saved.getMessage().getId()),
        NotificationType.chat);
    
    var users = userService.readAll(userService.query()
        .and(userService.getPredicate().withChat(saved.getParticipant().getChat().getId())))
      .getList();
    
    pushService.sendPush(users, message);
  }
}

