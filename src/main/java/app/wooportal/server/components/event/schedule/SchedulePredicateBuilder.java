package app.wooportal.server.components.event.schedule;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SchedulePredicateBuilder extends PredicateBuilder<QScheduleEntity, ScheduleEntity> {

  public SchedulePredicateBuilder() {
    super(QScheduleEntity.scheduleEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }
  public BooleanExpression withEvent(String event) {
    return query.event.name.equalsIgnoreCase(event);
  }
  public BooleanExpression withDate(OffsetDateTime date) {
    return query.startDate.after(date.withMinute(0).withHour(0))
      .and(query.startDate.before(date.withMinute(59).withHour(23)));
  }
}
