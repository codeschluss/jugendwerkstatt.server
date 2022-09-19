package app.wooportal.server.components.jobad.jobtype;

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
public class JobTypeApi extends CrudApi<JobTypeEntity, JobTypeService> {

  public JobTypeApi(JobTypeService JobTypeService) {
    super(JobTypeService);
  }

  @Override
  @GraphQLQuery(name = "getJobTypes")
  public PageableList<JobTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getJobType")
  public Optional<JobTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) JobTypeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveJobTypes")
  @AdminPermission
  public List<JobTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<JobTypeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveJobType")
  @AdminPermission
  public JobTypeEntity saveOne(@GraphQLArgument(name = CrudApi.entity) JobTypeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteJobTypes")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteJobType")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


