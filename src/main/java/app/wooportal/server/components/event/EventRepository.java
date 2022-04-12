package app.wooportal.server.components.event;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EventRepository extends DataRepository<EventEntity> {

}
