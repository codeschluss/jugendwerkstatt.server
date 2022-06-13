package app.wooportal.server.components.evaluation.answer;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AnswerPredicateBuilder extends PredicateBuilder<QAnswerEntity, AnswerEntity> {

  public AnswerPredicateBuilder() {
    super(QAnswerEntity.answerEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return (query.question.item.likeIgnoreCase(term));
  }
  public BooleanExpression withYear(Integer year) {
    return query.created.year().eq(year);
}
  
  public BooleanExpression withQuestionId(String courseId) {
    return query.question.id.likeIgnoreCase(courseId);
  }
}
