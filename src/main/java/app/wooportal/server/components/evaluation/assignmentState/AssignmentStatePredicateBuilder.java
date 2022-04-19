package app.wooportal.server.components.evaluation.assignmentState;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AssignmentStatePredicateBuilder
    extends PredicateBuilder<QAssignmentStateEntity, AssignmentStateEntity> {

  public AssignmentStatePredicateBuilder() {
    super(QAssignmentStateEntity.assignmentStateEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
