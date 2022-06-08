package app.wooportal.server.components.messaging.readReceipt;

import java.util.Map;
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
  protected void postSave(ReadReceiptEntity saved, ReadReceiptEntity newEntity, JsonNode context) {

    var message= new MessageDto(null, null,
        Map.of(NotificationType.readReceipt.toString(), saved.getId(),
            "ParticipantId", saved.getParticipant().getId(),
            "MessageId", saved.getMessage().getId(),
            "ChatId", saved.getMessage().getChat().getId()));
    
    pushService.sendPush(userService.getAllUsersInChat(saved.getMessage().getChat().getId()), message);
      }
    }

