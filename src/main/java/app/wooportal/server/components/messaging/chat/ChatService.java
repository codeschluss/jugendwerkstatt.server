package app.wooportal.server.components.messaging.chat;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.messaging.message.MessageEntity;
import app.wooportal.server.components.messaging.message.MessageService;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.services.AuthenticationService;

@Service
public class ChatService extends DataService<ChatEntity, ChatPredicateBuilder> {
  
  private final AuthenticationService authService;

  public ChatService(
      DataRepository<ChatEntity> repo,
      ChatPredicateBuilder predicate,
      ParticipantService participantService,
      AuthenticationService authService,
      MessageService messageService,
      MediaService mediaService) {
    super(repo, predicate);
    
    this.authService = authService;
    addService("participants", participantService);
    addService("messages", messageService);
    addService("avatar", mediaService);
  }

  public Optional<ChatEntity> getByParticipantUsersAndCurrentUser(
      Set<ParticipantEntity> participants) {
    if (participants != null && !participants.isEmpty()) {
      var userIds = new ArrayList<String>();
      userIds.addAll(participants.stream().map(p -> p.getUser().getId()).toList());
      userIds.add(authService.getAuthenticatedUser().get().getId());
      var result = repo.findAll(query()
            .and(predicate.withUsers(userIds.stream().distinct().toList()))
            .addGraph(graph("participants")))
          .getList();
      
      if (result != null && !result.isEmpty()) {
        for (var chat: result) {
          if (chat.getParticipants().size() == userIds.size()) {
            return Optional.of(chat);
          } 
        }
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
  public Optional<MessageEntity> getLastMessage(ChatEntity chat) {
    var messageService = getService(MessageService.class);
    var message = messageService.readAll(messageService.query()
        .and(messageService.getPredicate().withChat(chat.getId()))
        .sort(Direction.DESC, "created")
        .setLimit(1)).getList();
        
    return !message.isEmpty()
        ? Optional.of(message.get(0))
        : Optional.empty();
  }
}

