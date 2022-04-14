package app.wooportal.server.components.link.category;

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
  public List<LinkCategoryEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<LinkCategoryEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveLinkCategory")
  public LinkCategoryEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) LinkCategoryEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteLinkCategory")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteLinkCategory")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }

}


