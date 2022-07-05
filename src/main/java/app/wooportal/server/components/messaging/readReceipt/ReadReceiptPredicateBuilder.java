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
  
  public BooleanExpression withParticipant(String participantId) {
    return query.participant.id.eq(participantId);
  }

  public BooleanExpression withMessage(String chatId) {
    return query.message.id.equalsIgnoreCase(chatId);
  }
  
  public BooleanExpression withUser(String userId) {
    return query.participant.user.id.equalsIgnoreCase(userId);
  }
}
