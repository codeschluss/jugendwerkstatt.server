package app.wooportal.server.components.push.notification;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class NotificationService
    extends DataService<NotificationEntity, NotificationPredicateBuilder> {

  public NotificationService(DataRepository<NotificationEntity> repo,
      NotificationPredicateBuilder predicate) throws IOException {
    super(repo, predicate);
  }

  public List<NotificationEntity> olderThan14days() {
    var query = query();
    query.or(predicate.olderThan14Days());
    return repo.findAll(query).getList();
  }
}
