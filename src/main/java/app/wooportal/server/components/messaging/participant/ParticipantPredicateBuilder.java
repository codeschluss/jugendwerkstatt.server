package app.wooportal.server.components.messaging.participant;

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

  public BooleanExpression withChat(String chatId) {
    return query.chat.id.equalsIgnoreCase(chatId);
  }

  public BooleanExpression withUser(String userId) {
    return query.user.id.equalsIgnoreCase(userId);
  }

  public BooleanExpression witGroup(String groupId) {
    return query.chat.group.id.eq(groupId);
  }
  
}
