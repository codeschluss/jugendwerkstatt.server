package app.wooportal.server.core.security.components.user;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.event.base.EventService;
import app.wooportal.server.components.jobad.base.JobAdService;
import app.wooportal.server.components.push.subscription.SubscriptionService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.InvalidPasswordResetException;
import app.wooportal.server.core.error.exception.InvalidTokenException;
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
import app.wooportal.server.core.security.services.AuthenticationService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class UserService extends DataService<UserEntity, UserPredicateBuilder> {
  
  private final AuthenticationService authService;
  
  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  private final EventService eventService;
  
  private final JobAdService jobAdService;
  
  private final MediaService mediaService;
  
  private final RoleService roleService;
  
  public UserService(
      DataRepository<UserEntity> repo,
      UserPredicateBuilder predicate,
      AuthenticationService authService,
      BCryptPasswordEncoder bcryptPasswordEncoder,
      EventService eventService,
      JobAdService jobAdService,
      MediaService mediaService,
      PasswordResetService passwordResetService,
      RoleService roleService,
      SubscriptionService subscriptionService,
      VerificationService verificationService) {
    super(repo, predicate);

    this.authService = authService;
    this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    this.eventService = eventService;
    this.jobAdService = jobAdService;
    this.mediaService = mediaService;
    this.roleService = roleService;
    
    addService("passwordResets", passwordResetService);
    addService("profilePicture", mediaService);
    addService("subscriptions", subscriptionService);
    addService("uploads", mediaService);
    addService("verifications", verificationService);
  }

  public Optional<UserEntity> getByLoginName(String name) {
    return repo.findOne(predicate.withLoginName(name));
  }
  
  public Optional<UserEntity> me() {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      return getById(currentUser.get().getId());
    }
    return currentUser;
  }

  @Override
  public void preSave(UserEntity entity, UserEntity newEntity, JsonNode context) {
    if (newEntity.getPassword() != null && newEntity.getId() == null) {
      newEntity.setPassword(bcryptPasswordEncoder.encode(newEntity.getPassword()));
    }
    
    if (entity.getId() != null
        && (entity.getRoles() == null || entity.getRoles().isEmpty()) 
        && newEntity.getApproved() != null && newEntity.getApproved()) {
      newEntity.getRoles().add(roleService.getStudentRole());

      setContext("roles", context);
    }

    if (entity.getId() == null || entity.getId().isBlank()) {
      newEntity.setVerifications(new HashSet<>(List.of(new VerificationEntity())));
      setContext("verification", context);
      
      newEntity.setApproved(false);
      setContext("approved", context);
      
      newEntity.setVerified(false);
      setContext("verified", context);
    }
  }
  
  public Optional<UserEntity> saveMe(UserEntity entity) {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      entity.setId(currentUser.get().getId());
      return Optional.of(saveWithContext(entity));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> addJobAdFavorite(String jobAdId) {
    var currentUser = authService.getAuthenticatedUser();
    var jobAd = jobAdService.getById(jobAdId);
    if (currentUser.isPresent() && jobAd.isPresent()
        && !getById(currentUser.get().getId()).get().getFavoriteJobAds().contains(jobAd.get())) {
      getById(currentUser.get().getId()).get().getFavoriteJobAds().add(jobAd.get());
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> deleteJobAdFavorite(String jobAdId) {
    var currentUser = authService.getAuthenticatedUser();
    var jobAd = jobAdService.getById(jobAdId);
    if (currentUser.isPresent() && jobAd.isPresent()) {
      getById(currentUser.get().getId()).get().getFavoriteJobAds().remove(jobAd.get());
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> addEventFavorite(String eventId) {
    var currentUser = authService.getAuthenticatedUser();
    var event = eventService.getById(eventId);
    if (currentUser.isPresent() && event.isPresent() 
        && !getById(currentUser.get().getId()).get().getFavoriteEvents().contains(event.get())) {
      getById(currentUser.get().getId()).get().getFavoriteEvents().add(event.get());
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> deleteEventFavorite(String eventId) {
    var currentUser = authService.getAuthenticatedUser();
    var event = eventService.getById(eventId);
    if (currentUser.isPresent() && event.isPresent()) {
      getById(currentUser.get().getId()).get().getFavoriteEvents().remove(event.get());
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> addUploads(List<MediaEntity> uploads) {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      getById(currentUser.get().getId()).get().getUploads().addAll(
          mediaService.saveAll(uploads));
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }
  
  public Optional<UserEntity> deleteUpload(List<String> uploads) {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      mediaService.deleteById(uploads.toArray(new String[uploads.size()]));
      return Optional.of(repo.save(currentUser.get()));
    }
    return currentUser;
  }

  public Boolean createPasswordReset(String mailAddress) {
    var result = repo.findOne(predicate.withLoginName(mailAddress));

    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", mailAddress);
    }

    var copy = ReflectionUtils.copy(result.get());
    if (copy.getPasswordResets() != null) {
      copy.getPasswordResets().add(new PasswordResetEntity());
    } else {
      copy.setPasswordResets(new HashSet<>(List.of(new PasswordResetEntity())));
    }
    persist(result.get(), copy, createContext("passwordResets"));
    return true;
  }

  public Boolean resetPassword(String key, String password) {
    var passwordReset = getService(PasswordResetService.class).getByKey(key);
    if (passwordReset.isEmpty()) {
      throw new InvalidPasswordResetException("Password reset not requested", key);
    }
    var user = passwordReset.get().getUser();
    user.setPassword(bcryptPasswordEncoder.encode(password));
    var service = getService(PasswordResetService.class);
    service.deleteAll(service.query().and(service.getPredicate().withUser(user.getId())));
    repo.save(user);
    return true;
  }

  public Boolean createVerification(String mailAddress) {
    var result = repo.findOne(predicate.withLoginName(mailAddress));

    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", mailAddress);
    }

    if (result.get().getVerified()) {
      throw new AlreadyVerifiedException("Already verified");
    }

    var copy = ReflectionUtils.copy(result.get());
    if (copy.getVerifications() != null) {
      copy.getVerifications().add(new VerificationEntity());
    } else {
      copy.setVerifications(new HashSet<>(List.of(new VerificationEntity())));
    }
    persist(result.get(), copy, createContext("verifications"));
    return true;
  }

  public UserEntity verify(String key) {
    var verification = getService(VerificationService.class).getByKey(key);
    if (verification.isPresent()) {
      var user = verification.get().getUser();
      user.setVerified(true);
      var service = getService(VerificationService.class);
      service.deleteAll(service.query().and(service.getPredicate().withUser(user.getId())));
      return repo.save(user);
    }
    throw new InvalidVerificationException("Verification invalid", key);
  }

  public Optional<UserEntity> deleteMe(String password) {
    var currentUser = authService.authenticateCurrentUser(password);
    if (currentUser.isPresent()) {
      deleteById(currentUser.get().getUser().getId());
      return Optional.of(currentUser.get().getUser());
    }
    return Optional.empty();
  }

  public boolean changePassword(String newPassword) {
    var currentUser = authService.getAuthenticatedUser();
    if (currentUser.isPresent()) {
      currentUser.get().setPassword(bcryptPasswordEncoder.encode(newPassword));
      repo.save(currentUser.get());
      return true;
    }
    throw new InvalidTokenException("Token is invalid");
  }
}
