package app.wooportal.server.core.security.components.passwordReset;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PasswordResetBuilder extends PredicateBuilder<QPasswordResetEntity, PasswordResetEntity> {

  public PasswordResetBuilder() {
    super(QPasswordResetEntity.passwordResetEntity);
  }
  
  @Override
  public BooleanExpression freeSearch(String term) {
    return query.key.likeIgnoreCase(term);
  }
  
  public BooleanExpression withKey(String key) {
    return query.key.eq(key);
  }

  public BooleanExpression createdBefore(OffsetDateTime date) {
    return query.created.before(date);
  }

}
