package app.wooportal.server.components.documents.usertemplate;

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
public class UserTemplateApi extends CrudApi<UserTemplateEntity, UserTemplateService> {
  
  public UserTemplateApi(
      UserTemplateService UserTemplateService) {
    super(UserTemplateService);
  }
  
  @Override
  @GraphQLQuery(name = "getUserTemplates")
  @ApprovedAndVerifiedPermission
  public PageableList<UserTemplateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getUserTemplate")
  @ApprovedAndVerifiedPermission
  public Optional<UserTemplateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) UserTemplateEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveUserTemplates")
  @ApprovedAndVerifiedPermission
  public List<UserTemplateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<UserTemplateEntity> entities) {
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveUserTemplate")
  @ApprovedAndVerifiedPermission
  public UserTemplateEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) UserTemplateEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteUserTemplates")
  @ApprovedAndVerifiedPermission
  public Boolean deleteAll(
      @GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }
  
  @Override
  @GraphQLMutation(name = "deleteUserTemplate")
  @ApprovedAndVerifiedPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
}


