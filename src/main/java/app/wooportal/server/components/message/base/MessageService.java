package app.wooportal.server.components.message.base;

import java.util.Map;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.message.chat.ChatService;
import app.wooportal.server.components.message.participant.ParticipantService;
import app.wooportal.server.components.push.FirebasePushService;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MessageService extends DataService<MessageEntity, MessagePredicateBuilder> {

  private final ParticipantService participantService;
  private final FirebasePushService firebasePushService;
  private final ChatService chatService;

  public MessageService(DataRepository<MessageEntity> repo, MessagePredicateBuilder predicate,
      ParticipantService participantService, FirebasePushService firebasePushService,
      ChatService chatService) {
    super(repo, predicate);
    this.participantService = participantService;
    this.firebasePushService = firebasePushService;
    this.chatService = chatService;
  }

  public void postSave(MessageEntity message) {

    var firebaseMessage = new MessageDto();
    firebaseMessage.setContent(message.getContent());
    firebaseMessage.setTitle(chatService.getById(message.getChat().getId()).get().getName());

    for (var chatParticipant : participantService
        .getAllParticipants(message, "user", "subscriptions")) {

      for (var subscription : chatParticipant.getUser().getSubscriptions()) {
        firebasePushService.sendPush(
            subscription,
            firebaseMessage,
            Map.of("CHAT", message.getChat().getId()));

      }
    }
  }
}
