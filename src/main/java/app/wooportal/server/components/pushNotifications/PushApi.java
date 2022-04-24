package app.wooportal.server.components.pushNotifications;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.subscription.base.SubscriptionEntity;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import lombok.AllArgsConstructor;


@Component
@GraphQLApi
@AllArgsConstructor
public class PushApi {

  private final PushService PushService;

  @GraphQLMutation
  public void sendMessage(MessageDto message) {

    Optional<SubscriptionEntity> subscription = Optional.ofNullable(new SubscriptionEntity());
    Map<String, String> additionalData = new HashMap<>();

    PushService.sendPush(subscription, message, additionalData);
  }
}

