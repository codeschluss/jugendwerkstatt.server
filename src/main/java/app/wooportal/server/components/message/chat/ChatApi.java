package app.wooportal.server.components.message.chat;

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
public class ChatApi extends CrudApi<ChatEntity, ChatService> {

  public ChatApi(ChatService eventChatService) {
    super(eventChatService);
  }

  @Override
  @GraphQLQuery(name = "getChats")
  public PageableList<ChatEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getChat")
  public Optional<ChatEntity> readOne(@GraphQLArgument(name = CrudApi.entity) ChatEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveChats")
  public List<ChatEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ChatEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveChat")
  public ChatEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ChatEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteChats")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteChat")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}

