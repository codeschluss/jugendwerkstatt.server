package app.wooportal.server.components.documents.template;

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
public class TemplateApi extends CrudApi<TemplateEntity, TemplateService> {

  public TemplateApi(TemplateService TemplateService) {
    super(TemplateService);
  }

  @Override
  @GraphQLQuery(name = "getTemplates")
  public PageableList<TemplateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getTemplate")
  public Optional<TemplateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) TemplateEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveTemplates")
  public List<TemplateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<TemplateEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveTemplate")
  public TemplateEntity saveOne(@GraphQLArgument(name = CrudApi.entity) TemplateEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteTemplates")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteTemplate")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }

}
