package app.wooportal.server.components.evaluation.answer;

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
public class AnswerApi extends CrudApi<AnswerEntity, AnswerService> {

  public AnswerApi(AnswerService AnswerService) {
    super(AnswerService);
  }

  @Override
  @GraphQLQuery(name = "getAnswers")
  public PageableList<AnswerEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getAnswer")
  public Optional<AnswerEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) AnswerEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveAnswers")
  public List<AnswerEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<AnswerEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveAnswer")
  public AnswerEntity saveOne(@GraphQLArgument(name = CrudApi.entity) AnswerEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteAnswers")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteAnswer")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }

}


