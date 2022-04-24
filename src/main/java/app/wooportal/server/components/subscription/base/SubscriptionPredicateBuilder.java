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

  public BooleanExpression withAuth_Secret(String key) {
    return query.auth_secret.equalsIgnoreCase(key);
  }

  public BooleanExpression withSubscriptionType(String name) {
    return name != null && !name.isBlank() ? query.subscriptionType.name.equalsIgnoreCase(name) : null;
  }
  public BooleanExpression withUserId(String name) {
    return name != null && !name.isBlank() ? query.user.id.equalsIgnoreCase(name) : null;
  }
}
