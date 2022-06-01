package app.wooportal.server.components.messaging.readReceipt;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ReadReceiptPredicateBuilder
    extends PredicateBuilder<QReadReceiptEntity, ReadReceiptEntity> {

  public ReadReceiptPredicateBuilder() {
    super(QReadReceiptEntity.readReceiptEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }

  public BooleanExpression withChat(String event) {
    return query.message.id.equalsIgnoreCase(event);
  }

  public BooleanExpression withUser(String event) {
    return query.participant.id.equalsIgnoreCase(event);
  }
}
