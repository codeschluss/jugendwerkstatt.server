package app.wooportal.server.components.event.base;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventPredicateBuilder extends PredicateBuilder<QEventEntity, EventEntity> {

  public EventPredicateBuilder() {
    super(QEventEntity.eventEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term)
        .or(query.id.likeIgnoreCase(term))
        .or(query.description.likeIgnoreCase(term))
        .or(query.category.name.likeIgnoreCase(term))
        .or(query.organizer.name.likeIgnoreCase(term))
        .or(query.organizer.mail.likeIgnoreCase(term))
        .or(query.organizer.website.likeIgnoreCase(term));
  }
  
  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
  
  public BooleanExpression withDate(OffsetDateTime date) {
    return query.schedules.any().startDate.after(date.withMinute(0).withHour(0))
      .and(query.schedules.any().startDate.before(date.withMinute(59).withHour(23)));
  }
}
