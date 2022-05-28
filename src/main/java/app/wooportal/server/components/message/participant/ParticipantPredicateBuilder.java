package app.wooportal.server.components.message.participant;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ParticipantPredicateBuilder
    extends PredicateBuilder<QParticipantEntity, ParticipantEntity> {

  public ParticipantPredicateBuilder() {
    super(QParticipantEntity.participantEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }

  public BooleanExpression withChat(String event) {
    return query.chat.name.equalsIgnoreCase(event);
  }
  
  public BooleanExpression withChatId(String chatId) {
    return query.chat.id.equalsIgnoreCase(chatId);
  }

  public BooleanExpression withUser(String event) {
    return query.user.id.equalsIgnoreCase(event);
  }
}
