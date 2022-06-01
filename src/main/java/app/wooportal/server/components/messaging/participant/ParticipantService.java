package app.wooportal.server.components.messaging.participant;

import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.messaging.message.MessageEntity;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ParticipantService
    extends DataService<ParticipantEntity, ParticipantPredicateBuilder> {

  public ParticipantService(DataRepository<ParticipantEntity> repo,
      ParticipantPredicateBuilder predicate) {
    super(repo, predicate);

  }

  public List<ParticipantEntity> getAllParticipants(MessageEntity message, String... graph) {
    var query = query();
    query.or(predicate.withChatId(message.getChat().getId()));
    
    return repo.findAll(query.addGraph(graph(graph))).getList();
  }
}
