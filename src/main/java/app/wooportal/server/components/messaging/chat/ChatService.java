package app.wooportal.server.components.messaging.chat;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.messaging.call.CallEntity;
import app.wooportal.server.components.messaging.call.CallService;
import app.wooportal.server.components.messaging.message.MessageEntity;
import app.wooportal.server.components.messaging.message.MessageService;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AuthenticationService;

@Service
public class ChatService extends DataService<ChatEntity, ChatPredicateBuilder> {
  
  private final AuthenticationService authService;
  private final CallService callService;
  private final UserService userService;
  private final ParticipantService participantService;

  public ChatService(
      DataRepository<ChatEntity> repo,
      ChatPredicateBuilder predicate,
      AuthenticationService authService,
      CallService callService,
      MessageService messageService,
      MediaService mediaService,
      ParticipantService participantService,
      UserService userService) {
    super(repo, predicate);
    
    this.authService = authService;
    this.callService = callService;
    this.userService = userService;
    this.participantService = participantService;
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
  
  public Optional<CallEntity> getLastCall(ChatEntity chat) {
    var call = callService.readAll(callService.query()
        .and(callService.getPredicate().withChat(chat.getId()))
        .sort(Direction.DESC, "created")
        .setLimit(1)).getList();
        
    return !call.isEmpty()
        ? Optional.of(call.get(0))
        : Optional.empty();
  }
  
  public boolean addParticipant(String userId, String chatId) {
    var chat = getById(chatId);
    var user = userService.getById(userId);

    if (chat.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("chat or user does not exist", chatId);
    }
    var participant = new ParticipantEntity();
    participant.setChat(chat.get());
    participant.setUser(user.get());
    participantService.save(participant);

    return true;
  }
  
  public boolean removeParticipant(String userId, String chatId) {
    var chat = getById(chatId);
    var user = userService.getById(userId);
    
    if (chat.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("chat or user does not exist", chatId);
    }
    var participants = participantService
        .readAll(participantService.query().and(participantService.getPredicate().withUser(userId))
            .and(participantService.getPredicate().withChat(chatId)))
        .getList();

    participantService.deleteAll(participants);

    return true;
  }
}

