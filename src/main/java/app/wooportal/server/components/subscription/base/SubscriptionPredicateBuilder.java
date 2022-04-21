package app.wooportal.server.components.subscription.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SubscriptionPredicateBuilder
    extends PredicateBuilder<QSubscriptionEntity, SubscriptionEntity> {

  public SubscriptionPredicateBuilder() {
    super(QSubscriptionEntity.subscriptionEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.auth_secret.likeIgnoreCase(term)
        .or(query.subscriptionType.name.likeIgnoreCase(term))
        .or(query.subscriptionType.description.likeIgnoreCase(term));
  }

  public BooleanExpression withAuth_Secret(String name) {
    return query.auth_secret.equalsIgnoreCase(name);
  }

}
