package app.wooportal.server.components.event.schedule;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.components.event.base.EventEntity;
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
  
  public BooleanExpression withEventId(String eventId) {
    return query.event.id.equalsIgnoreCase(eventId);
  }
  public BooleanExpression withEvent(EventEntity event) {
    return query.event.eq(event);
  }
  
<<<<<<< Upstream, based on main
  public BooleanExpression withStartDateAfter(OffsetDateTime date) {
    return query.startDate.after(date);
  }
  
  public BooleanExpression withStartDate(OffsetDateTime date) {
=======
  public BooleanExpression withNotPastDates(OffsetDateTime date) {
    return query.startDate.after(date);
  }
  
  public BooleanExpression withDate(OffsetDateTime date) {
>>>>>>> c09ab6a #257, 246, 248
    return query.startDate.after(date.withMinute(0).withHour(0))
      .and(query.startDate.before(date.withMinute(59).withHour(23)));
  }
}
