package app.wooportal.server.components.documents.templateType;

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
public class TemplateTypeApi extends CrudApi<TemplateTypeEntity, TemplateTypeService> {

  public TemplateTypeApi(TemplateTypeService TemplateTypeService) {
    super(TemplateTypeService);
  }

  @Override
  @GraphQLQuery(name = "getTemplateTypes")
  public PageableList<TemplateTypeEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getTemplateType")
  public Optional<TemplateTypeEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) TemplateTypeEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveTemplateTypes")
  public List<TemplateTypeEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<TemplateTypeEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveTemplateType")
  public TemplateTypeEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) TemplateTypeEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteTemplateTypes")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteTemplateType")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }
}


