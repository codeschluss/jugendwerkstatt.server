package app.wooportal.server.components.event.schedule;

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
}