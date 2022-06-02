package app.wooportal.server.components.push;

import java.util.List;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.services.AuthorizationService;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import io.leangen.graphql.spqr.spring.util.ConcurrentMultiMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

@GraphQLApi
@Component
public class PushApi {

  private final AuthorizationService authService;
  
  private final PushService pushService;
  
  private final ConcurrentMultiMap<String, FluxSink<MessageDto>> subscribers = new ConcurrentMultiMap<>();

  public PushApi(
      AuthorizationService authService,
      PushService pushService) {
    this.authService = authService;
    this.pushService = pushService;
  }

  @GraphQLMutation(name = "sendGlobalPush")
  @AdminPermission
  public Boolean sendGlobalPush(MessageDto message) {
    pushService.sendGlobalPush(message);
    return true;
  }
  
  @GraphQLSubscription
  public Publisher<MessageDto> addListener(String token) {
    var user = authService.getUserFromToken(token);
    if (user.isEmpty()) {
      throw new InvalidTokenException("Invalid token, either user doesn't exist or token invalid", token);
    }
    
    //TODO: Put in GraphQlPushService
    return Flux.create(
        subscriber -> subscribers.add(user.get().getId(),
            subscriber.onDispose(() -> subscribers
                .remove(user.get().getId(), subscriber))),
        FluxSink.OverflowStrategy.LATEST);
  }
  
  public void sendPush(List<UserEntity> users, MessageDto message) {
    for (var user: users) {
      subscribers.get(user.getId())
        .forEach(subscriber -> subscriber.next(message));
    }
  }
}


