package app.wooportal.server.components.message.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class MessageService extends DataService<MessageEntity, MessagePredicateBuilder> {

  public MessageService(DataRepository<MessageEntity> repo, MessagePredicateBuilder predicate) {
    super(repo, predicate);

  }
}
