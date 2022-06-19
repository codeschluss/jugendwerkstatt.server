package app.wooportal.server.components.messaging.message;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AuthenticationService;

@Service
public class MessageService extends DataService<MessageEntity, MessagePredicateBuilder> {

  private final AuthenticationService authService;
  private final UserService userService;
  private final PushService pushService;

  public MessageService(
      DataRepository<MessageEntity> repo,
      MessagePredicateBuilder predicate,
      AuthenticationService authService,
      UserService userService,
      PushService pushService) {
    super(repo, predicate);
    this.authService = authService;
    this.userService = userService;
    this.pushService = pushService;
  }
  
  @Override
  protected void preSave(MessageEntity entity, MessageEntity newEntity, JsonNode context) {
    var currentUser = this.authService.getAuthenticatedUser();
    
    if (currentUser.isPresent()) {
      newEntity.setUser(currentUser.get());
      setContext("user", context);
    }
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
    
    users.removeIf(user -> user.getId().equals(saved.getUser().getId()));
    pushService.sendPush(users, message);
  }
}
