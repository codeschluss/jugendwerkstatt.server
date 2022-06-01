package app.wooportal.server.components.messaging.message;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.messaging.chat.ChatService;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MessageService extends DataService<MessageEntity, MessagePredicateBuilder> {

  private final ParticipantService participantService;
  private final PushService pushService;
  private final ChatService chatService;


  public MessageService(DataRepository<MessageEntity> repo, MessagePredicateBuilder predicate,
      ParticipantService participantService, PushService pushService,
      ChatService chatService) {
    super(repo, predicate);
    this.participantService = participantService;
    this.pushService = pushService;
    this.chatService = chatService;
  }

  @Override
  protected void postSave(MessageEntity saved, MessageEntity newEntity, JsonNode context) {

    var firebaseMessage = new MessageDto();
    firebaseMessage.setContent(saved.getContent());
    firebaseMessage.setTitle(chatService.getById(saved.getChat().getId()).get().getName());
    
    for (var chatParticipant : participantService
        .getAllParticipants(saved, "user", "subscriptions")) {

        pushService.sendPush(firebaseMessage,
            chatParticipant.getUser().getSubscriptions(),
                Map.of(NotificationType.message.toString(), saved.getChat().getId()));
      }
    }
  }
