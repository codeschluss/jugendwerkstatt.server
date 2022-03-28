package app.wooportal.server.core.security.components.user;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class UserPredicateBuilder extends PredicateBuilder<QUserEntity, UserEntity> {

  public UserPredicateBuilder() {
    super(QUserEntity.userEntity);
  }
  
  @Override
  public BooleanExpression freeSearch(String term) {
    return query.loginName.likeIgnoreCase(term)
        .or(query.roles.any().name.likeIgnoreCase(term));
  }

  public BooleanExpression withLoginName(String loginName) {
    return query.loginName.equalsIgnoreCase(loginName);
  }

  public BooleanExpression withRole(String name) {
    return query.roles.any().name.equalsIgnoreCase(name);
  }

}
