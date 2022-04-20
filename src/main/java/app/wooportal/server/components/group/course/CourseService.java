package app.wooportal.server.components.group.course;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class CourseService extends DataService<CourseEntity, CoursePredicateBuilder> {

  public CourseService(DataRepository<CourseEntity> repo, CoursePredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<CourseEntity> getExisting(CourseEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<CourseEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}
