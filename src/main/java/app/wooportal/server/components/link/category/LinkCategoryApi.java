package app.wooportal.server.components.link.category;

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
public class LinkCategoryApi extends CrudApi<LinkCategoryEntity, LinkCategoryService> {

  public LinkCategoryApi(LinkCategoryService LinkService) {
    super(LinkService);
  }

  @Override
  @GraphQLQuery(name = "getLinkCategories")
  public PageableList<LinkCategoryEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getLinkCategory")
  public Optional<LinkCategoryEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) LinkCategoryEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveLinkCategories")
  @AdminPermission
  public List<LinkCategoryEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<LinkCategoryEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveLinkCategory")
  @AdminPermission
  public LinkCategoryEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) LinkCategoryEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteLinkCategory")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteLinkCategory")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


