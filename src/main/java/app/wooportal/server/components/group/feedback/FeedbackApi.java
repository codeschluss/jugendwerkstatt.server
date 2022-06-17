package app.wooportal.server.components.group.feedback;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.push.MessageDto;
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
public class FeedbackApi extends CrudApi<FeedbackEntity, FeedbackService> {

  public FeedbackApi(FeedbackService feedbackService) {
    super(feedbackService);
  }

  @Override
  @GraphQLQuery(name = "getFeedbacks")
  @ApprovedAndVerifiedPermission
  public PageableList<FeedbackEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getFeedback")
  @ApprovedAndVerifiedPermission
  public Optional<FeedbackEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) FeedbackEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveFeedbacks")
  @ApprovedAndVerifiedPermission
  public List<FeedbackEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<FeedbackEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveFeedback")
  @ApprovedAndVerifiedPermission
  public FeedbackEntity saveOne(@GraphQLArgument(name = CrudApi.entity) FeedbackEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteFeedbacks")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteFeedback")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
}


