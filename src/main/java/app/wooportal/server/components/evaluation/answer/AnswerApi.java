package app.wooportal.server.components.evaluation.answer;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class AnswerApi extends CrudApi<AnswerEntity, AnswerService> {

  public AnswerApi(AnswerService AnswerService) {
    super(AnswerService);
  }

  @Override
  @GraphQLQuery(name = "getAnswers")
  @ApprovedAndVerifiedPermission
  public PageableList<AnswerEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAnswer")
  @ApprovedAndVerifiedPermission
  public Optional<AnswerEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AnswerEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAnswers")
  @ApprovedAndVerifiedPermission
  public List<AnswerEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AnswerEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAnswer")
  @ApprovedAndVerifiedPermission
  public AnswerEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AnswerEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAnswers")
  @ApprovedAndVerifiedPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteAnswer")
  @ApprovedAndVerifiedPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


