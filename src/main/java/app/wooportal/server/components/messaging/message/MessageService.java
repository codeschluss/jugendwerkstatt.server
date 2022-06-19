package app.wooportal.server.components.messaging.message;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.messaging.chat.ChatService;
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
  private final ChatService chatService;

  public MessageService(
      DataRepository<MessageEntity> repo,
      MessagePredicateBuilder predicate,
      AuthenticationService authService,
      UserService userService,
      PushService pushService,
      ChatService chatService) {
    super(repo, predicate);
    this.authService = authService;
    this.userService = userService;
    this.pushService = pushService;
    this.chatService = chatService;
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
        chatService.getById(saved.getChat().getId()).get().getName(),saved.getContent(),
        Map.of(NotificationType.chat.toString(), saved.getChat().getId()),
        NotificationType.chat);
    
    var users = userService.readAll(userService.query()
        .addGraph(userService.graph("subscriptions"))
        .and(userService.getPredicate().withChat(saved.getChat().getId()))).getList();
    
    pushService.sendPush(users, message);
  }
}
