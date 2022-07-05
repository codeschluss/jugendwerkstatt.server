package app.wooportal.server.components.messaging.message;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AuthenticationService;

@Service
public class MessageService extends DataService<MessageEntity, MessagePredicateBuilder> {

  private final AuthenticationService authService;
  private final UserService userService;
  private final PushService pushService;
  private final ParticipantService participantService;

  public MessageService(
      DataRepository<MessageEntity> repo,
      MessagePredicateBuilder predicate,
      AuthenticationService authService,
      UserService userService,
      PushService pushService,
      MediaService mediaService,
      ParticipantService participantService) {
    super(repo, predicate);
    this.authService = authService;
    this.userService = userService;
    this.pushService = pushService;
    this.participantService = participantService;
    
    addService("media", mediaService);
    addService("parent", this);
  }
  
  @Override
  protected void preSave(MessageEntity entity, MessageEntity newEntity, JsonNode context) {
    var currentUser = this.authService.getAuthenticatedUser();

    if (currentUser.isEmpty() || newEntity.getChat() == null) {
      throw new BadParamsException("chat or user does not exist", newEntity);
    }

    var result = participantService.readAll(participantService.query()
          .and(participantService.getPredicate().withUser(currentUser.get().getId())
          .and(participantService.getPredicate().withChat(newEntity.getChat().getId())))
          .setLimit(1))
        .getList();
    
    if (result.isEmpty()) {
      throw new BadParamsException(
          "No participant exists for user and chat", currentUser.get(), newEntity.getChat());
    }
    
    newEntity.setParticipant(result.get(0));
    setContext("participant", context);
  }

  @Override
  protected void postSave(MessageEntity saved, MessageEntity newEntity, JsonNode context) {
    var message = new MessageDto(
        saved.getChat().getName(),
        saved.getContent(),
        Map.of(
            NotificationType.chat.toString(),
            saved.getChat().getId()),
        NotificationType.chat);
    
    var users = userService.readAll(userService.query()
          .addGraph(userService.graph("subscriptions"))
          .and(userService.getPredicate().withChat(saved.getChat().getId())))
        .getList();
    
    users.removeIf(user -> user.getId().equals(saved.getParticipant().getUser().getId()));
    pushService.sendPush(users, message);
  }
}
