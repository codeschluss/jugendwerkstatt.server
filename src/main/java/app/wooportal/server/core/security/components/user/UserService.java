package app.wooportal.server.core.security.components.user;

import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.evaluation.assignment.AssignmentEntity;
import app.wooportal.server.components.evaluation.assignment.AssignmentService;
import app.wooportal.server.components.event.base.EventService;
import app.wooportal.server.components.jobad.base.JobAdService;
import app.wooportal.server.components.push.subscription.SubscriptionService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.InvalidPasswordResetException;
import app.wooportal.server.core.error.exception.InvalidVerificationException;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.media.base.MediaEntity;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetEntity;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetService;
import app.wooportal.server.core.security.components.role.RoleService;
import app.wooportal.server.core.security.components.verification.VerificationEntity;
import app.wooportal.server.core.security.components.verification.VerificationService;
import app.wooportal.server.core.security.services.AuthorizationService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class UserService extends DataService<UserEntity, UserPredicateBuilder> {

  private final AssignmentService assignmentService;
  
  private final AuthorizationService authorizationService;
  
  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  private final EventService eventService;
  
  private final JobAdService jobAdService;
  
  private final MediaService mediaService;
  
  private final RoleService roleService;
  
  public UserService(
      AssignmentService assignmentService,
      DataRepository<UserEntity> repo,
      UserPredicateBuilder predicate,
      AuthorizationService authorizationService,
      BCryptPasswordEncoder bcryptPasswordEncoder,
      EventService eventService,
      JobAdService jobAdService,
      MediaService mediaService,
      PasswordResetService passwordResetService,
      RoleService roleService,
      SubscriptionService subscriptionService,
      VerificationService verificationService) {
    super(repo, predicate);

    this.assignmentService = assignmentService;
    this.authorizationService = authorizationService;
    this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    this.eventService = eventService;
    this.jobAdService = jobAdService;
    this.mediaService = mediaService;
    this.roleService = roleService;
    addService("passwordReset", passwordResetService);
    addService("profilePicture", mediaService);
    addService("subscriptions", subscriptionService);
    addService("uploads", mediaService);
    addService("verification", verificationService);
  }

  @Override
  public Optional<UserEntity> getExisting(UserEntity entity) {
    return entity.getEmail() == null || entity.getEmail().isEmpty() ? Optional.empty()
        : getByLoginName(entity.getEmail());
  }

  public Optional<UserEntity> getByLoginName(String name) {
    return repo.findOne(predicate.withLoginName(name));
  }

  @Override
  public void preSave(UserEntity entity, UserEntity newEntity, JsonNode context) {
    if (newEntity.getPassword() != null) {
      newEntity.setPassword(bcryptPasswordEncoder.encode(newEntity.getPassword()));
    }

    if (entity.getId() == null || entity.getId().isBlank()) {
      newEntity.setVerification(new VerificationEntity());
    }
  }
  
  public Optional<UserEntity> addJobAdFavorite(String jobAdId) {
    var currentUser = authorizationService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      getById(currentUser.get().getId()).get().getFavoriteJobAds().add(jobAdService.getById(jobAdId).get());
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> me() {
    var currentUser = authorizationService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      return getById(currentUser.get().getId());
    }
    return currentUser;
  }
  
  public Optional<UserEntity> addEventFavorite(String eventId) {
    var currentUser = authorizationService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      getById(currentUser.get().getId()).get().getFavoriteEvents().add(eventService.getById(eventId).get());
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> addUploads(List<MediaEntity> uploads) {
    var currentUser = authorizationService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      getById(currentUser.get().getId()).get().getUploads().addAll(
          mediaService.saveAll(uploads));
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> addAssignments(List<AssignmentEntity> assignments) {
    var currentUser = authorizationService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      getById(currentUser.get().getId()).get().getAssignments().addAll(
          assignmentService.saveAll(assignments));
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> approve(String userId) {
    var user = getById(userId);
    
    if (user.isPresent()) {
      user.get().getRoles().add(roleService.getApprovedRole());
      return Optional.of(repo.save(user.get()));
    }
    return user;
  }

  public Boolean createPasswordReset(String mailAddress) {
    var result = repo.findOne(predicate.withLoginName(mailAddress));

    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", mailAddress);
    }

    if (result.get().getPasswordReset() != null) {
      getService(PasswordResetService.class).deleteById(result.get().getPasswordReset().getId());
    }

    var copy = ReflectionUtils.copy(result.get());
    copy.setPasswordReset(new PasswordResetEntity());
    persist(result.get(), copy, createContext("passwordReset"));
    return true;
  }

  public Boolean resetPassword(String key, String password) {
    var passwordReset = getService(PasswordResetService.class).getByKey(key);
    if (passwordReset.isEmpty()) {
      throw new InvalidPasswordResetException("Password reset not requested", key);
    }
    var user = passwordReset.get().getUser();
    user.setPassword(bcryptPasswordEncoder.encode(password));
    repo.save(user);
    return true;
  }

  public Boolean createVerification(String mailAddress) {
    var result = repo.findOne(predicate.withLoginName(mailAddress));

    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", mailAddress);
    }

    if (result.get().getRoles().stream()
        .anyMatch(r -> r.getName().equalsIgnoreCase(RoleService.verified))) {
      throw new AlreadyVerifiedException("Already verified");
    }

    if (result.get().getVerification() != null) {
      getService(VerificationService.class).deleteById(result.get().getVerification().getId());
    }

    var copy = ReflectionUtils.copy(result.get());
    copy.setVerification(new VerificationEntity());
    persist(result.get(), copy, createContext("verification"));
    return true;
  }

  public UserEntity verify(String key) {
    var verification = getService(VerificationService.class).getByKey(key);
    if (verification.isPresent()) {
      var user = verification.get().getUser();
      user.getRoles().add(roleService.getVerifiedRole());
      getService(VerificationService.class).deleteById(verification.get().getId());
      return repo.save(user);
    }
    throw new InvalidVerificationException("Verification invalid", key);
  }

}
