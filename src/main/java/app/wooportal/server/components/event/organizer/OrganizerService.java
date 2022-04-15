package app.wooportal.server.components.event.organizer;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class OrganizerService extends DataService<OrganizerEntity, OrganizerPredicateBuilder> {

  public OrganizerService(
      DataRepository<OrganizerEntity> repo, 
      OrganizerPredicateBuilder predicate) {
    super(repo, predicate);

  }
  @Override
  public Optional<OrganizerEntity> getExisting(OrganizerEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty()
        ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<OrganizerEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}

