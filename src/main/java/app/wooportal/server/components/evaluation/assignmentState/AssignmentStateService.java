package app.wooportal.server.components.evaluation.assignmentState;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AssignmentStateService extends DataService<AssignmentStateEntity, AssignmentStatePredicateBuilder> {

  public final static String assigend = "ASSIGNED";
  
  public final static String done = "DONE";
  
  public AssignmentStateService(
      DataRepository<AssignmentStateEntity> repo, 
      AssignmentStatePredicateBuilder predicate) {
    super(repo, predicate);

  }
  @Override
  public Optional<AssignmentStateEntity> getExisting(AssignmentStateEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty()
        ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<AssignmentStateEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
  
  public AssignmentStateEntity getAssignedState() {
    return repo.findOne(predicate.withName(assigend)).get();
  }
  
  public AssignmentStateEntity getDoneState() {
    return repo.findOne(predicate.withName(done)).get();
  }
}

