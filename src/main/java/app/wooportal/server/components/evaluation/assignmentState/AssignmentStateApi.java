package app.wooportal.server.components.evaluation.assignmentState;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
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
  public PageableList<AssignmentStateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAssignmentState")
  public Optional<AssignmentStateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AssignmentStateEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAssignmentStates")
  public List<AssignmentStateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AssignmentStateEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAssignmentState")
  public AssignmentStateEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) AssignmentStateEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAssignmentStates")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteAssignmentState")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }

}


