package app.wooportal.server.components.link.base;

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
public class LinkApi extends CrudApi<LinkEntity, LinkService> {
  
  public LinkApi(
      LinkService LinkService) {
    super(LinkService);
  }
  
  @Override
  @GraphQLQuery(name = "getLinks")
  public PageableList<LinkEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getLink")
  public Optional<LinkEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) LinkEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveLinks")
  public List<LinkEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<LinkEntity> entities) {
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveLink")
  public LinkEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) LinkEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteLinks")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }
  
  @Override
  @GraphQLMutation(name = "deleteLink")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
}


