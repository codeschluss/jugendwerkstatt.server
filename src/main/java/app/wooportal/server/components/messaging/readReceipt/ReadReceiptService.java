package app.wooportal.server.components.messaging.readReceipt;

import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class ReadReceiptService extends DataService<ReadReceiptEntity, ReadReceiptPredicateBuilder> {
  
  private final PushService pushService;
  private final UserService userService;

  public ReadReceiptService(DataRepository<ReadReceiptEntity> repo,
      ReadReceiptPredicateBuilder predicate,
      PushService pushService,
      UserService userService) {
    super(repo, predicate);

    this.pushService = pushService;
    this.userService = userService;  
  }
  
  @Override
  public Optional<ReadReceiptEntity> getExisting(ReadReceiptEntity entity) {
    return entity.getParticipant() == null || entity.getMessage() == null
        ? Optional.empty()
        : getByParticipantAndMessage(
            entity.getParticipant().getId(),
            entity.getMessage().getId());
  }
  
  private Optional<ReadReceiptEntity> getByParticipantAndMessage(String participantId, String messageId) {
    return repo.findOne(
        predicate.withParticipantAndMessage(participantId, messageId));
  }

  @Override
  protected void postSave(ReadReceiptEntity saved, ReadReceiptEntity newEntity, JsonNode context) {
    var message = new MessageDto(null, null,
        Map.of(NotificationType.chat.toString(), saved.getId(),
            "ParticipantId", saved.getParticipant().getId(),
            "MessageId", saved.getMessage().getId()),
        NotificationType.chat);
    
    var users = userService.readAll(userService.query()
        .addGraph(userService.graph("subscriptions"))
        .and(userService.getPredicate().withMessage(saved.getMessage().getId()))).getList();
    
    pushService.sendPush(users, message);
  }
}

