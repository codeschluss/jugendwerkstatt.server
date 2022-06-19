package app.wooportal.server.components.messaging.chat;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.services.AuthenticationService;

@Service
public class ChatService extends DataService<ChatEntity, ChatPredicateBuilder> {
  
  private final AuthenticationService authService;

  public ChatService(
      DataRepository<ChatEntity> repo,
      ChatPredicateBuilder predicate,
      ParticipantService participantService,
      AuthenticationService authService) {
    super(repo, predicate);
    
    this.authService = authService;
    addService("participants", participantService);
  }

  public Optional<ChatEntity> getByParticipantUsersAndCurrentUser(
      Set<ParticipantEntity> participants) {
    if (participants != null && !participants.isEmpty()) {
      var userIds = new ArrayList<String>();
      userIds.addAll(participants.stream().map(p -> p.getUser().getId()).toList());
      var result = repo.findAll(query()
            .and(predicate.withUsers(userIds.stream().distinct().toList())))
          .getList();
      
      if (result != null && !result.isEmpty()) {
        return Optional.of(result.get(0));
      }
    }
    return Optional.empty();
  }

  @Override
  public void preSave(ChatEntity entity, ChatEntity newEntity, JsonNode context) {
    var currentUser = authService.getAuthenticatedUser();
    
    if (newEntity.getAdmin() == null) {
      newEntity.setAdmin(false);
      setContext("admin", context);
    }
    
    if (currentUser.isPresent()
        && newEntity.getParticipants() != null 
        && !newEntity.getParticipants().isEmpty()) {
      var participant = new ParticipantEntity();
      participant.setUser(currentUser.get());
      newEntity.getParticipants().add(participant);
      setContext("participants", context);
    }
  }
}

