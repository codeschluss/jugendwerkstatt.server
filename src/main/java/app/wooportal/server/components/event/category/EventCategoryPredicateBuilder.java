package app.wooportal.server.components.event.category;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class EventCategoryPredicateBuilder extends PredicateBuilder<QEventCategoryEntity, EventCategoryEntity> {

  public EventCategoryPredicateBuilder() {
    super(QEventCategoryEntity.eventCategoryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
