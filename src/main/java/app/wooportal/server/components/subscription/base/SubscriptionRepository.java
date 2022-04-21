package app.wooportal.server.components.subscription.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SubscriptionRepository extends DataRepository<SubscriptionEntity> {

}
