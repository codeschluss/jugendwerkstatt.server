package app.wooportal.server.components.event.schedule;

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
public class ScheduleApi extends CrudApi<ScheduleEntity, ScheduleService> {
  
  public ScheduleApi(
      ScheduleService ScheduleService) {
    super(ScheduleService);
  }
  
  @Override
  @GraphQLQuery(name = "getSchedules")
  public PageableList<ScheduleEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getSchedule")
  public Optional<ScheduleEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ScheduleEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveSchedules")
  public List<ScheduleEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ScheduleEntity> entities) {
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveSchedule")
  public ScheduleEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ScheduleEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteSchedules")
  public void deleteAll(
      @GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }
  
  @GraphQLMutation(name = "deleteSchedule")
  public void delete(
      @GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }
  
  }


