package app.wooportal.server.components.push;

import org.springframework.stereotype.Component;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class PushApi {

  private final PushService pushService;

  public PushApi(PushService pushService) {
    this.pushService = pushService;
  }

  @GraphQLMutation(name = "pushNotification")
  public Boolean pushNotification(MessageDto message) {
    pushService.sendNotification(message);
    return true;
  }
}


