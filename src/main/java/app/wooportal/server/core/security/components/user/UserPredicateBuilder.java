package app.wooportal.server.core.security.components.user;

import java.time.OffsetDateTime;
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
    return query.email.likeIgnoreCase(term)
        .or(query.roles.any().name.likeIgnoreCase(term).or(query.course.name.likeIgnoreCase(term)));
  }

  public BooleanExpression withLoginName(String loginName) {
    return loginName != null && !loginName.isBlank() ? query.email.equalsIgnoreCase(loginName)
        : null;
  }

  public BooleanExpression withRole(String name) {
    return name != null && !name.isBlank() ? query.roles.any().name.equalsIgnoreCase(name) : null;
  }

  public BooleanExpression withCourseNotNull() {
    return query.course.isNotNull();
  }

  public BooleanExpression createdBefore(OffsetDateTime date) {
    return date != null ? query.created.before(date) : null;
  }

  public BooleanExpression notVerified() {
    return query.verification.isNotNull();
  }
  public BooleanExpression withChat(String chatId) {
    return query.participants.any().chat.id.eq(chatId);
  }
}
