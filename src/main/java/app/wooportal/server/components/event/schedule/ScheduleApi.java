package app.wooportal.server.components.event.schedule;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ScheduleApi extends CrudApi<ScheduleEntity, ScheduleService> {

  public ScheduleApi(ScheduleService ScheduleService) {
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
  @AdminPermission
  public List<ScheduleEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ScheduleEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveSchedule")
  @AdminPermission
  public ScheduleEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ScheduleEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteSchedules")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteSchedule")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


