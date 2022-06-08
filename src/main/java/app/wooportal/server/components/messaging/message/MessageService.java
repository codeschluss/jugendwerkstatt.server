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

@Service
public class MessageService extends DataService<MessageEntity, MessagePredicateBuilder> {

  private final UserService userService;
  private final PushService pushService;
  private final ChatService chatService;


  public MessageService(DataRepository<MessageEntity> repo, MessagePredicateBuilder predicate,
      UserService userService, PushService pushService,
      ChatService chatService) {
    super(repo, predicate);
    this.userService = userService;
    this.pushService = pushService;
    this.chatService = chatService;
    
  }

  @Override
  protected void postSave(MessageEntity saved, MessageEntity newEntity, JsonNode context) {

    var message = new MessageDto(
        chatService.getById(saved.getChat().getId()).get().getName(),saved.getContent(),
        Map.of(NotificationType.message.toString(), saved.getChat().getId()));

     {
        pushService.sendPush(userService.getAllUsersInChat(saved.getChat().getId()), message);
      }
    }
  }
