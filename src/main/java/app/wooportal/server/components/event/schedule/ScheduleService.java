package app.wooportal.server.components.event.schedule;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.event.base.EventEntity;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.base.Query;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ScheduleService extends DataService<ScheduleEntity, SchedulePredicateBuilder> {

  public ScheduleService(DataRepository<ScheduleEntity> repo, SchedulePredicateBuilder predicate) {
    super(repo, predicate);

  }

  public List<ScheduleEntity> withDates(List<OffsetDateTime> dates, String... graph) {
    var query = query();
    for (var date : dates) {
      query.or(predicate.withStartDate(date));
    }
    return repo.findAll(query.addGraph(graph(graph))).getList();
  }
}

