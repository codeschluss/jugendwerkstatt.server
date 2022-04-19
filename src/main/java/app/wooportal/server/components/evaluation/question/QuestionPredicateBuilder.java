package app.wooportal.server.components.evaluation.question;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class QuestionPredicateBuilder extends PredicateBuilder<QQuestionEntity, QuestionEntity> {

  public QuestionPredicateBuilder() {
    super(QQuestionEntity.questionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.item.likeIgnoreCase(term)
        .or(query.questionnaire.name.likeIgnoreCase(term));
  }

  public BooleanExpression withItem(String name) {
    return query.item.equalsIgnoreCase(name);
  }
}
