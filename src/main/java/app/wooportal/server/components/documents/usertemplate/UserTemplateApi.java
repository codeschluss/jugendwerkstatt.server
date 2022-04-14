package app.wooportal.server.components.documents.usertemplate;

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
public class UserTemplateApi extends CrudApi<UserTemplateEntity, UserTemplateService> {
  
  public UserTemplateApi(
      UserTemplateService UserTemplateService) {
    super(UserTemplateService);
  }
  
  @Override
  @GraphQLQuery(name = "getUserTemplates")
  public PageableList<UserTemplateEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getUserTemplate")
  public Optional<UserTemplateEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) UserTemplateEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveUserTemplates")
  public List<UserTemplateEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<UserTemplateEntity> entities) {
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveUserTemplate")
  public UserTemplateEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) UserTemplateEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteUserTemplates")
  public void deleteAll(
      @GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }
  
  @GraphQLMutation(name = "deleteUserTemplate")
  public void delete(
      @GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }
  
  }


