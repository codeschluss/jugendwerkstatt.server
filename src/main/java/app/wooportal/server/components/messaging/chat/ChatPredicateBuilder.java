package app.wooportal.server.components.messaging.chat;

import java.util.List;
import org.springframework.stereotype.Service;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;


@Service
public class ChatPredicateBuilder extends PredicateBuilder<QChatEntity, ChatEntity> {

  public ChatPredicateBuilder() {
    super(QChatEntity.chatEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }

  public Predicate withUsers(List<String> userIds) {
    var builder = new BooleanBuilder();
    
    for (var userId : userIds) {
      builder.and(query.participants.any().user.id.eq(userId));
    }
    
    return builder.getValue();
  }
}
