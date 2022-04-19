package app.wooportal.server.components.evaluation.questionnaire;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class QuestionnairePredicateBuilder
    extends PredicateBuilder<QQuestionnaireEntity, QuestionnaireEntity> {

  public QuestionnairePredicateBuilder() {
    super(QQuestionnaireEntity.questionnaireEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
