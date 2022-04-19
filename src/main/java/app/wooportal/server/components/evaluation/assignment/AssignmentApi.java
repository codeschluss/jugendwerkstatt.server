package app.wooportal.server.components.evaluation.assignment;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class AssignmentApi extends CrudApi<AssignmentEntity, AssignmentService> {

  public AssignmentApi(AssignmentService AssignmentService) {
    super(AssignmentService);
  }

  @Override
  @GraphQLQuery(name = "getAssignments")
  public PageableList<AssignmentEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAssignment")
  public Optional<AssignmentEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AssignmentEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAssignments")
  public List<AssignmentEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AssignmentEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAssignment")
  public AssignmentEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AssignmentEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAssignments")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteAssignment")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }

}


