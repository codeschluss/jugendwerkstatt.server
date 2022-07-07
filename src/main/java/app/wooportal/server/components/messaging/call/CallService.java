package app.wooportal.server.components.messaging.call;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.services.AuthenticationService;

@Service
public class CallService extends DataService<CallEntity, CallPredicateBuilder> {
  
  private final AuthenticationService authService;
  private final ParticipantService participantService;

  public CallService(
      DataRepository<CallEntity> repo,
      CallPredicateBuilder predicate,
      AuthenticationService authService,
      ParticipantService participantService) {
    super(repo, predicate);
    
    this.authService = authService;
    this.participantService = participantService;
    
    
  }
  @Override
  protected void preSave(CallEntity entity, CallEntity newEntity, JsonNode context) {
    if (entity.getId() == null) {
      var currentUser = this.authService.getAuthenticatedUser();

      if (currentUser.isEmpty() || newEntity.getChat() == null) {
        throw new BadParamsException("chat or user does not exist", newEntity);
      }

      var result = participantService.readAll(participantService.query()
            .and(participantService.getPredicate().withUser(currentUser.get().getId())
            .and(participantService.getPredicate().withChat(newEntity.getChat().getId())))
            .setLimit(1))
          .getList();
      
      if (result.isEmpty()) {
        throw new BadParamsException(
            "No participant exists for user and chat", currentUser.get(), newEntity.getChat());
      }
      
      newEntity.setInitiator(result.get(0));
      setContext("participant", context);
    }
  }
}

