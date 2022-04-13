package app.wooportal.server.components.event.base;

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
public class EventApi extends CrudApi<EventEntity, EventService> {
  
  public EventApi(
      EventService EventService) {
    super(EventService);
  }
  
  @Override
  @GraphQLQuery(name = "getEvents")
  public PageableList<EventEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getEvent")
  public Optional<EventEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) EventEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveEvents")
  public List<EventEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<EventEntity> entities) {
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveEvent")
  public EventEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) EventEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteEvents")
  public void deleteAll(
      @GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }
  
  @GraphQLMutation(name = "deleteEvent")
  public void delete(
      @GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }
  
  }

