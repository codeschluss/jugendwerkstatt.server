package app.wooportal.server.components.event.organizer;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class OrganizerApi extends CrudApi<OrganizerEntity, OrganizerService> {

  public OrganizerApi(OrganizerService OrganizerService) {
    super(OrganizerService);
  }

  @Override
  @GraphQLQuery(name = "getOrganizers")
  public PageableList<OrganizerEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getOrganizer")
  public Optional<OrganizerEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) OrganizerEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveOrganizers")
  @AdminPermission
  public List<OrganizerEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<OrganizerEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveOrganizer")
  @AdminPermission
  public OrganizerEntity saveOne(@GraphQLArgument(name = CrudApi.entity) OrganizerEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganizers")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteOrganizer")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


