package app.wooportal.server.core.security.components.user;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.exception.NotFoundException;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class UserApi extends CrudApi<UserEntity, UserService> {
  
  public UserApi(
      UserService userService) {
    super(userService);
  }
  
  @Override
  @GraphQLQuery(name = "getUsers")
  public PageableList<UserEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }
  
  @Override
  @GraphQLQuery(name = "getUser")
  public Optional<UserEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return super.readOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "saveUsers")
  public List<UserEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<UserEntity> entities) {
    return super.saveAll(entities);
  }
  
  @Override
  @GraphQLMutation(name = "saveUser")
  public UserEntity saveOne(
      @GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return super.saveOne(entity);
  }
  
  @Override
  @GraphQLMutation(name = "deleteUsers")
  public void deleteAll(
      @GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }
  
  @GraphQLMutation(name = "deleteUser")
  public void delete(
      @GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }
  
  @GraphQLMutation(name = "sendPasswordReset")
  public void forgetPassword(String mailAddress) {
    service.createPasswordReset(mailAddress);
  }
  
  @GraphQLMutation(name = "resetPassword")
  public void resetPassword(String key, String password) {
    service.resetPassword(key, password);
  }
  
  @GraphQLMutation(name = "sendVerification")
  public void sendVerification(String mailAddress) {
    service.createVerification(mailAddress);
  }
  
  @GraphQLMutation(name = "verify")
  public UserEntity verify(String key) {
    return service.verify(key);
  }
  
  @GraphQLMutation(name = "test")
  public UserEntity test() {
    throw new NotFoundException("test", "test");
  }

}
