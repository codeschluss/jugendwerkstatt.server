package app.wooportal.server.core.security.components.user;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.evaluation.assignment.AssignmentEntity;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
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
  @AdminPermission
  public PageableList<UserEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getUser")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return super.readOne(entity);
  }
  
  @GraphQLQuery(name = "me")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> me() {
    return service.me();
  }
  
  @Override
  @GraphQLMutation(name = "saveUsers")
  @AdminPermission
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
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }
  
  @Override
  @GraphQLMutation(name = "deleteUser")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLMutation(name = "addAssignments")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> addAssignments(List<AssignmentEntity> assignments) {
    return service.addAssignments(assignments);
  }
  
  @GraphQLMutation(name = "addJobAdFavorite")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> addJobAdFavorite(String jobAdId) {
    return service.addJobAdFavorite(jobAdId);
  }
  
  @GraphQLMutation(name = "deleteJobAdFavorite")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> deleteJobAdFavorite(String jobAdId) {
    return service.deleteJobAdFavorite(jobAdId);
  }
  
  @GraphQLMutation(name = "addEventFavorite")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> addEventFavorite(String eventId) {
    return service.addEventFavorite(eventId);
  }
  
  @GraphQLMutation(name = "deleteEventFavorite")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> deleteEventFavorite(String eventId) {
    return service.deleteEventFavorite(eventId);
  }
  
  @GraphQLMutation(name = "addRoles")
  @AdminPermission
  public Optional<UserEntity> addRoles(String userId, List<String> roleIds) {
    return service.addRoles(userId, roleIds);
  }
  
  @GraphQLMutation(name = "addUploads")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> addUploads(List<MediaEntity> uploads) {
    return service.addUploads(uploads);
  }
  
  @GraphQLMutation(name = "deleteUploads")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> deleteUpload(MediaEntity upload) {
    return service.deleteUpload(upload);
  }
  
  @GraphQLMutation(name = "sendPasswordReset")
  public Boolean forgetPassword(String mailAddress) {
    return service.createPasswordReset(mailAddress);
  }
  
  
  @GraphQLMutation(name = "resetPassword")
  public Boolean resetPassword(String key, String password) {
    return service.resetPassword(key, password);
  }
  
  @GraphQLMutation(name = "sendVerification")
  public Boolean sendVerification(String mailAddress) {
    return service.createVerification(mailAddress);
  }
  
  @GraphQLMutation(name = "verify")
  public UserEntity verify(String key) {
    return service.verify(key);
  }
}
