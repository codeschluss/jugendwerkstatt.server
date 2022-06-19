package app.wooportal.server.components.messaging.chat;

import java.util.List;
import org.springframework.stereotype.Service;
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

  public BooleanExpression withUsers(List<String> list) {
    // TODO Auto-generated method stub
    return null;
  }
}
