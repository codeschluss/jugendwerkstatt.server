package app.wooportal.server.components.evaluation.questionnaire;

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
public class QuestionnaireApi extends CrudApi<QuestionnaireEntity, QuestionnaireService> {

  public QuestionnaireApi(QuestionnaireService QuestionnaireService) {
    super(QuestionnaireService);
  }

  @Override
  @GraphQLQuery(name = "getQuestionnaires")
  public PageableList<QuestionnaireEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getQuestionnaire")
  public Optional<QuestionnaireEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) QuestionnaireEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveQuestionnaires")
  public List<QuestionnaireEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<QuestionnaireEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveQuestionnaire")
  public QuestionnaireEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) QuestionnaireEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestionnaires")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteQuestionnaire")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


