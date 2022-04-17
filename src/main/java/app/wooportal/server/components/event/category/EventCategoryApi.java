package app.wooportal.server.components.event.category;

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
public class EventCategoryApi extends CrudApi<EventCategoryEntity, EventCategoryService> {
  
  public EventCategoryApi(
      EventCategoryService CategoryService) {
    super(CategoryService);
  }
  
  @Override
  @GraphQLQuery(name = "getCategorys")
  public PageableList<EventCategoryEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getCategory")
  public Optional<EventCategoryEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventCategoryEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveCategorys")
  public List<EventCategoryEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventCategoryEntity> entities) {
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveCategory")
  public EventCategoryEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) EventCategoryEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteCategorys")
  public void deleteAll(
      @GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }
  
  @GraphQLMutation(name = "deleteCategory")
  public void delete(
      @GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }
  
  }


