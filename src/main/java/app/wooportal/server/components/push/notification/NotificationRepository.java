package app.wooportal.server.components.push.notification;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
interface NotificationRepository extends DataRepository<NotificationEntity> {

}
