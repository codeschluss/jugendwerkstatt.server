package app.wooportal.server.components.settings;

import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SettingsApi extends CrudApi<SettingsEntity, SettingsService> {
  
  public SettingsApi(
      SettingsService SettingService) {
    super(SettingService);
  }
  
  @Override
  @GraphQLQuery(name = "getSettings")
  @ApprovedAndVerifiedPermission
  public Optional<SettingsEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) SettingsEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveSettings")
  @AdminPermission
  public SettingsEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) SettingsEntity entity) {
    return super.saveOne(entity);
  }
  
}


