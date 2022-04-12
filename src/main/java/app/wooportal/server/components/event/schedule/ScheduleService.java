package app.wooportal.server.components.event.schedule;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class ScheduleService extends DataService<ScheduleEntity, SchedulePredicateBuilder> {

  public ScheduleService(ScheduleRepository repo, SchedulePredicateBuilder predicate) {
    super(repo, predicate);

  }

}

