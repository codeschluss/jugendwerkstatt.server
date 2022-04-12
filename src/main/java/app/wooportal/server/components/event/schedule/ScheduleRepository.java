package app.wooportal.server.components.event.schedule;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ScheduleRepository extends DataRepository<ScheduleEntity> {

}
