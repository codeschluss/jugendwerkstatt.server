package app.wooportal.server.components.push.notification;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class NotificationPredicateBuilder
    extends PredicateBuilder<QNotificationEntity, NotificationEntity> {

  public NotificationPredicateBuilder() {
    super(QNotificationEntity.notificationEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.title.likeIgnoreCase(term).or(query.id.likeIgnoreCase(term))
        .or(query.content.likeIgnoreCase(term));
  }

  public BooleanExpression olderThan(Integer days) {
    return query.created.before(OffsetDateTime.now().minusDays(days));
  }

  public BooleanExpression read() {
    return query.read.isTrue();
  }
}
