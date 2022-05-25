package app.wooportal.server.components.push.notification;

import java.io.IOException;
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
}
