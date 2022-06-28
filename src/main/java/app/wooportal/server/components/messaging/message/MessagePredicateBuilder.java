package app.wooportal.server.components.messaging.message;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class MessagePredicateBuilder extends PredicateBuilder<QMessageEntity, MessageEntity> {

  public MessagePredicateBuilder() {
    super(QMessageEntity.messageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.content.likeIgnoreCase(term).or(query.id.likeIgnoreCase(term))
        .or(query.chat.id.likeIgnoreCase(term));
  }

  public BooleanExpression withContent(String name) {
    return query.content.equalsIgnoreCase(name);
  }
  public BooleanExpression withChat(String chatId) {
    return query.chat.id.equalsIgnoreCase(chatId);
  }
  public BooleanExpression withStartDateAfter(OffsetDateTime date) {
    return query.created.after(date);
  }
}
