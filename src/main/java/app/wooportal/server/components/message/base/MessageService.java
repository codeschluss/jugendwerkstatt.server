package app.wooportal.server.components.message.base;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.message.participant.ParticipantService;
import app.wooportal.server.components.push.FirebasePushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MessageService extends DataService<MessageEntity, MessagePredicateBuilder> {

  private final ParticipantService participantService;
  private final FirebasePushService firebasePushService;

  public MessageService(DataRepository<MessageEntity> repo, MessagePredicateBuilder predicate, ParticipantService participantService,
      FirebasePushService firebasePushService) {
    super(repo, predicate);
    this.participantService = participantService;
    this.firebasePushService = firebasePushService;
  }

  public void postSave(MessageEntity message) {

    var chatId = message.getChat().getId();
    for (var chatParticipant : (participantService.getAllParticipants(chatId))) {

      for (var subscription : chatParticipant.getUser().getSubscriptions()) {
        firebasePushService.sendPush(subscription, null, null);
      }
    }
  }
}
