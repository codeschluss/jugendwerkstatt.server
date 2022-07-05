package app.wooportal.server.components.messaging.call;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;


@Service
public class CallPredicateBuilder extends PredicateBuilder<QCallEntity, CallEntity> {

  public CallPredicateBuilder() {
    super(QCallEntity.callEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }
  public BooleanExpression withChat(String chatId) {
    return query.chat.id.equalsIgnoreCase(chatId);
  }
}
