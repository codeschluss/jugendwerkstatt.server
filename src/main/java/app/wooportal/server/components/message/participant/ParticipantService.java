package app.wooportal.server.components.message.participant;

import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class ParticipantService
    extends DataService<ParticipantEntity, ParticipantPredicateBuilder> {

  public ParticipantService(DataRepository<ParticipantEntity> repo,
      ParticipantPredicateBuilder predicate) {
    super(repo, predicate);

  }

  public List<ParticipantEntity> getAllParticipants(String chatId) {
    return repo.findAll(query(false).and(predicate.withChatId(chatId))).getList();
  }
 
}

