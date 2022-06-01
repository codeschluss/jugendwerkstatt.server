package app.wooportal.server.components.messaging.participant;

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
public class ParticipantApi extends CrudApi<ParticipantEntity, ParticipantService> {

  public ParticipantApi(ParticipantService ParticipantService) {
    super(ParticipantService);
  }

  @Override
  @GraphQLQuery(name = "getParticipants")
  public PageableList<ParticipantEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getParticipant")
  public Optional<ParticipantEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ParticipantEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveParticipants")
  public List<ParticipantEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ParticipantEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveParticipant")
  public ParticipantEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) ParticipantEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteParticipants")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteParticipant")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


