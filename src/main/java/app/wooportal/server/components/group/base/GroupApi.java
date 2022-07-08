package app.wooportal.server.components.group.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class GroupApi extends CrudApi<GroupEntity, GroupService> {

  public GroupApi(GroupService GroupService) {
    super(GroupService);
  }

  @Override
  @GraphQLQuery(name = "getGroups")
  @ApprovedAndVerifiedPermission
  public PageableList<GroupEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getGroup")
  @ApprovedAndVerifiedPermission
  public Optional<GroupEntity> readOne(@GraphQLArgument(name = CrudApi.entity) GroupEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveGroups")
  @AdminPermission
  public List<GroupEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<GroupEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveGroup")
  @AdminPermission
  public GroupEntity saveOne(@GraphQLArgument(name = CrudApi.entity) GroupEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteGroups")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteGroup")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}
