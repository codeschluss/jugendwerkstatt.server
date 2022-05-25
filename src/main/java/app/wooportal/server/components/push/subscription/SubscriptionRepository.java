package app.wooportal.server.components.push.subscription;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SubscriptionRepository extends DataRepository<SubscriptionEntity> {

}
