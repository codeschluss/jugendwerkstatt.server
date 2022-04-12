package app.wooportal.server.components.event;

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
    return query.name.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }

  public BooleanExpression withOrganizer(String organizer) {
    return query.organizer.name.equalsIgnoreCase(organizer);
  }

  public BooleanExpression withCategory(String organizer) {
    return query.organizer.name.equalsIgnoreCase(organizer);
  }

}
