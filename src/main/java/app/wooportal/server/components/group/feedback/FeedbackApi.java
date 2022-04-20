package app.wooportal.server.components.group.feedback;

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
public class FeedbackApi extends CrudApi<FeedbackEntity, FeedbackService> {

  public FeedbackApi(FeedbackService FeedbackService) {
    super(FeedbackService);
  }

  @Override
  @GraphQLQuery(name = "getFeedbacks")
  public PageableList<FeedbackEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getFeedback")
  public Optional<FeedbackEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) FeedbackEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveFeedbacks")
  public List<FeedbackEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<FeedbackEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveFeedback")
  public FeedbackEntity saveOne(@GraphQLArgument(name = CrudApi.entity) FeedbackEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteFeedbacks")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteFeedback")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }
}

