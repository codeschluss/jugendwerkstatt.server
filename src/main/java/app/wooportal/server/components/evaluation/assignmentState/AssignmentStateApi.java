package app.wooportal.server.components.evaluation.assignmentState;

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
public class AssignmentStateApi extends CrudApi<AssignmentStateEntity, AssignmentStateService> {

  public AssignmentStateApi(AssignmentStateService AssignmentStateService) {
    super(AssignmentStateService);
  }

  @Override
  @GraphQLQuery(name = "getAssignmentStates")
  @ApprovedAndVerifiedPermission
  public PageableList<AssignmentStateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAssignmentState")
  @ApprovedAndVerifiedPermission
  public Optional<AssignmentStateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AssignmentStateEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAssignmentStates")
  @AdminPermission
  public List<AssignmentStateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AssignmentStateEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAssignmentState")
  @AdminPermission
  public AssignmentStateEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) AssignmentStateEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAssignmentStates")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteAssignmentState")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


