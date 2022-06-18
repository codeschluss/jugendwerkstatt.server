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
  
  @GraphQLQuery(name = "getSettings")
  @ApprovedAndVerifiedPermission
  public Optional<SettingsEntity> readOne() {
    var settings = service.readAll(service.query());
    return settings != null && !settings.isEmpty()
        ? Optional.of(settings.get(0))
        : Optional.empty();
  }
  
  @Override
  @GraphQLMutation(name = "saveSettings")
  @AdminPermission
  public SettingsEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) SettingsEntity entity) {
    var settings = readOne();
    return settings.isPresent()
        ? service.persist(settings.get(), entity, null)
        : null;
  }
  
}


