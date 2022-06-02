package app.wooportal.server.components.messaging.message;

import java.util.List;
import java.util.Optional;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.exception.InvalidTokenException;
import app.wooportal.server.core.security.services.AuthorizationService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class MessageApi extends CrudApi<MessageEntity, MessageService> {
  
  private final AuthorizationService authService;

  public MessageApi(
      MessageService MessageService,
      AuthorizationService authService) {
    super(MessageService);
    
    this.authService = authService;
  }

  @Override
  @GraphQLQuery(name = "getMessages")
  public PageableList<MessageEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getMessage")
  public Optional<MessageEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) MessageEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveMessages")
  public List<MessageEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<MessageEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveMessage")
  public MessageEntity saveOne(@GraphQLArgument(name = CrudApi.entity) MessageEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteMessages")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteMessage")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLSubscription
  public Publisher<MessageEntity> incomingMessages(String token) {
    var user = authService.getUserFromToken(token);
    if (user.isEmpty()) {
      throw new InvalidTokenException("Invalid token, either user doesn't exist or token invalid", token);
    }
    
    //TODO:
    return null;
  }
}


