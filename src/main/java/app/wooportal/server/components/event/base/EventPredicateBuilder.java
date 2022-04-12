package app.wooportal.server.components.event.base;

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
        .or(query.description.likeIgnoreCase(term))
        .or(query.category.name.likeIgnoreCase(term))
        .or(query.organizer.name.likeIgnoreCase(term))
        .or(query.organizer.mail.likeIgnoreCase(term))
        .or(query.organizer.website.likeIgnoreCase(term));
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }

}
