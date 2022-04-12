package app.wooportal.server.components.event.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EventRepository extends DataRepository<EventEntity> {

}
