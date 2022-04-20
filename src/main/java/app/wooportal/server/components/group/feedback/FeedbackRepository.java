package app.wooportal.server.components.group.feedback;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface FeedbackRepository extends DataRepository<FeedbackEntity> {

}
