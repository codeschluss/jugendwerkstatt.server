package app.wooportal.server.components.group.base;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class GroupService extends DataService<GroupEntity, GroupPredicateBuilder> {

  public GroupService(
      DataRepository<GroupEntity> repo,
      GroupPredicateBuilder predicate,
      CourseService courseService) {
    super(repo, predicate);

    addService("courses", courseService);
  }

  @Override
  public Optional<GroupEntity> getExisting(GroupEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<GroupEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}
