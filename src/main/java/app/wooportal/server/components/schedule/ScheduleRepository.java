package app.wooportal.server.components.schedule;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ScheduleRepository extends DataRepository<ScheduleEntity> {

}
