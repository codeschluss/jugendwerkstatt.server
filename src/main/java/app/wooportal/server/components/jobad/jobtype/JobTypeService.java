package app.wooportal.server.components.jobad.jobtype;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class JobTypeService extends DataService<JobTypeEntity, JobTypePredicateBuilder> {

  public JobTypeService(
      DataRepository<JobTypeEntity> repo, 
      JobTypePredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<JobTypeEntity> getExisting(JobTypeEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() 
        ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<JobTypeEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}
