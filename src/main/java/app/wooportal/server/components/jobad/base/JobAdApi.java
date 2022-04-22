package app.wooportal.server.components.jobad.base;

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
public class JobAdApi extends CrudApi<JobAdEntity, JobAdService> {

  public JobAdApi(JobAdService JobAdService) {
    super(JobAdService);
  }

  @Override
  @GraphQLQuery(name = "getJobAds")
  public PageableList<JobAdEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getJobAd")
  public Optional<JobAdEntity> readOne(@GraphQLArgument(name = CrudApi.entity) JobAdEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveJobAds")
  public List<JobAdEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<JobAdEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveJobAd")
  public JobAdEntity saveOne(@GraphQLArgument(name = CrudApi.entity) JobAdEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteJobAds")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteJobAd")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}
