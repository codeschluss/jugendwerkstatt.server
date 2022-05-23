package app.wooportal.server.components.event.base;

import org.springframework.stereotype.Repository;
import app.wooportal.server.core.repository.DataRepository;

@Repository
interface EventRepository extends DataRepository<EventEntity> {

}
