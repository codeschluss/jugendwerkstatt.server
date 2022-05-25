package app.wooportal.server.components.message.participant;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ParticipantService
    extends DataService<ParticipantEntity, ParticipantPredicateBuilder> {

  public ParticipantService(DataRepository<ParticipantEntity> repo,
      ParticipantPredicateBuilder predicate) {
    super(repo, predicate);

  }

}

