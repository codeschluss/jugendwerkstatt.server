package app.wooportal.server.components.push;

import org.springframework.stereotype.Component;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class PushApi {

  private final PushService pushService;

  public PushApi(PushService pushService) {
    this.pushService = pushService;
  }

  @GraphQLMutation(name = "sendGlobalPush")
  @AdminPermission
  public Boolean sendGlobalPush(PushDto message) {
    pushService.sendGlobalPush(message);
    return true;
  }
}


