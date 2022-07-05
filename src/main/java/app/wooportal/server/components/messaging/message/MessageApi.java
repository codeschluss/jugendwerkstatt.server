package app.wooportal.server.components.messaging.message;

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
public class MessageApi extends CrudApi<MessageEntity, MessageService> {

  public MessageApi(
      MessageService MessageService) {
    super(MessageService);
  }

  @Override
  @GraphQLQuery(name = "getMessages")
  @ApprovedAndVerifiedPermission
  public PageableList<MessageEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getMessage")
  @ApprovedAndVerifiedPermission
  public Optional<MessageEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MessageEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveMessages")
  @ApprovedAndVerifiedPermission
  public List<MessageEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MessageEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveMessage")
  @ApprovedAndVerifiedPermission
  public MessageEntity saveOne(@GraphQLArgument(name = CrudApi.entity) MessageEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteMessages")
  @ApprovedAndVerifiedPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteMessage")
  @ApprovedAndVerifiedPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


