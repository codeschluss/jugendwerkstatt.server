package app.wooportal.server.components.messaging.chat;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ChatService extends DataService<ChatEntity, ChatPredicateBuilder> {

  public ChatService(DataRepository<ChatEntity> repo, ChatPredicateBuilder predicate) {
    super(repo, predicate);
  }

  
}

