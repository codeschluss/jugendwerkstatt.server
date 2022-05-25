package app.wooportal.server.components.documents.template;

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
public class TemplateApi extends CrudApi<TemplateEntity, TemplateService> {

  public TemplateApi(TemplateService TemplateService) {
    super(TemplateService);
  }

  @Override
  @GraphQLQuery(name = "getTemplates")
  @ApprovedAndVerifiedPermission
  public PageableList<TemplateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getTemplate")
  @ApprovedAndVerifiedPermission
  public Optional<TemplateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) TemplateEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveTemplates")
  @AdminPermission
  public List<TemplateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<TemplateEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveTemplate")
  @AdminPermission
  public TemplateEntity saveOne(@GraphQLArgument(name = CrudApi.entity) TemplateEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteTemplates")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteTemplate")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}
