package app.wooportal.server.components.organizer;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class OrganizerPredicateBuilder extends PredicateBuilder<QOrganizerEntity, OrganizerEntity> {

  public OrganizerPredicateBuilder() {
    super(QOrganizerEntity.organizerEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
