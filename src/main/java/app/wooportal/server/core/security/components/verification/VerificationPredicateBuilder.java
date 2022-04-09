package app.wooportal.server.core.security.components.verification;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class VerificationPredicateBuilder extends PredicateBuilder<QVerificationEntity, VerificationEntity> {

  public VerificationPredicateBuilder() {
    super(QVerificationEntity.verificationEntity);
  }
  
  @Override
  public BooleanExpression freeSearch(String term) {
    return query.key.likeIgnoreCase(term);
  }
  
  public BooleanExpression withKey(String key) {
    return query.key.eq(key);
  }

}
