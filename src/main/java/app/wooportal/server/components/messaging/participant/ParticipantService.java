package app.wooportal.server.components.messaging.participant;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.messaging.chat.ChatEntity;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class ParticipantService
    extends DataService<ParticipantEntity, ParticipantPredicateBuilder> {

  public ParticipantService(DataRepository<ParticipantEntity> repo,
      ParticipantPredicateBuilder predicate) {
    super(repo, predicate);

  }
  
  @Override
  public Optional<ParticipantEntity> getExisting(ParticipantEntity entity) {
    return entity.getUser() != null && entity.getChat() != null
        ? getByUserAndChat(entity.getUser(), entity.getChat())
        : Optional.empty();
  }

  private Optional<ParticipantEntity> getByUserAndChat(UserEntity user, ChatEntity chat) {
    return repo.findOne(predicate.withUser(user.getId())
        .and(predicate.withChat(chat.getId())));
  }
}
