package app.wooportal.server.core.security.components.user;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.security.components.role.RoleService;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class UserApi extends CrudApi<UserEntity, UserService> {

  private final PushService pushService;

  public UserApi(UserService userService, PushService pushService) {
    super(userService);

    this.pushService = pushService;
  }

  @Override
  @GraphQLQuery(name = "getUsers")
  @ApprovedAndVerifiedPermission
  public PageableList<UserEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getUser")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> readOne(@GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
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
  public UserEntity saveOne(@GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return super.saveOne(entity);
  }

  @GraphQLMutation(name = "saveMe")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> saveMe(@GraphQLArgument(name = CrudApi.entity) UserEntity entity) {
    return service.saveMe(entity);
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

  @GraphQLMutation(name = "deleteMe")
  @ApprovedAndVerifiedPermission
  public Boolean deleteMe(String password) {
    var deletedUser = service.deleteMe(password);

    if (deletedUser.isPresent()) {
      var message = new MessageDto(
          "Benutzer gelöscht",
          "Benutzer mit dem Namen: " + deletedUser.get().getFullname() + " hat soeben das Benutzerkonto gelöscht",
          NotificationType.deletedUser);

      pushService.sendPush(
          service.readAll(service.query().and(service.getPredicate().withRole(RoleService.admin)))
              .getList(),
          message);
      return true;
    }
    return false;
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

  @GraphQLMutation(name = "addUploads")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> addUploads(List<MediaEntity> uploads) {
    return service.addUploads(uploads);
  }

  @GraphQLMutation(name = "deleteUploads")
  @ApprovedAndVerifiedPermission
  public Optional<UserEntity> deleteUpload(List<String> uploadIds) {
    return service.deleteUpload(uploadIds);
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
  
  @GraphQLMutation(name = "changePassword")
  @ApprovedAndVerifiedPermission
  public Boolean changePassword(String newPassword) {
    return service.changePassword(newPassword);
  }
}
