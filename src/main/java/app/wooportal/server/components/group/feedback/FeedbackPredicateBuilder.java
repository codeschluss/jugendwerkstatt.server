package app.wooportal.server.components.group.feedback;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class FeedbackPredicateBuilder extends PredicateBuilder<QFeedbackEntity, FeedbackEntity> {

  public FeedbackPredicateBuilder() {
    super(QFeedbackEntity.feedbackEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return (query.course.name.likeIgnoreCase(term));
  }

  public BooleanExpression withFullName(String name) {
    return query.user.fullname.equalsIgnoreCase(name);
  }
}
