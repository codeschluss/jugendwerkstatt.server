package app.wooportal.server.components.evaluation.assignment;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AssignmentPredicateBuilder
    extends PredicateBuilder <QAssignmentEntity, AssignmentEntity> {

  public AssignmentPredicateBuilder() {
    super(QAssignmentEntity.assignmentEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.assignmentState.name.likeIgnoreCase(term)
        .or(query.questionnaire.name.likeIgnoreCase(term))
        .or(query.user.fullname.likeIgnoreCase(term));
  }

}
