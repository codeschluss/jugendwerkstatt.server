package app.wooportal.server.core.security.components.user;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.InvalidPasswordReset;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.error.exception.InvalidVerificationException;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetEntity;
import app.wooportal.server.core.security.components.passwordReset.PasswordResetService;
import app.wooportal.server.core.security.components.role.RoleService;
import app.wooportal.server.core.security.components.verification.VerificationEntity;
import app.wooportal.server.core.security.components.verification.VerificationService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class UserService extends DataService<UserEntity, UserPredicateBuilder> {

  private final BCryptPasswordEncoder bcryptPasswordEncoder;
  
  private final RoleService roleService;
  
  public UserService(
      UserRepository repo,
      UserPredicateBuilder predicate,
      BCryptPasswordEncoder encoder,
      RoleService roleService,
      PasswordResetService passwordResetService,
      VerificationService verificationService) {
    super(repo, predicate);
    
    this.bcryptPasswordEncoder = encoder;
    this.roleService = roleService;
    addService("passwordReset", passwordResetService);
    addService("verification", verificationService);
  }
  
  @Override
  public Optional<UserEntity> getExisting(UserEntity entity) {
    return entity.getLoginName() == null || entity.getLoginName().isEmpty()
        ? Optional.empty()
        : getByLoginName(entity.getLoginName());
  }

  public Optional<UserEntity> getByLoginName(String name) {
    return repo.findOne(predicate.withLoginName(name));
  }
  
  public List<UserEntity> getNotVerifiedBefore(OffsetDateTime date) {
    return repo.findAll(query(false)
          .and(predicate.createdBefore(date))
          .and(predicate.notVerified())
        ).getList();
  }
  
  @Override
  public void preSave(
      UserEntity entity,
      UserEntity newEntity, 
      JsonNode context) {
    if (newEntity.getPassword() != null) {
      newEntity.setPassword(bcryptPasswordEncoder.encode(newEntity.getPassword()));
    }
    
    if (entity.getId() == null || entity.getId().isBlank()) {
      newEntity.setVerification(new VerificationEntity());
    }
  }
  
  public void createPasswordReset(String mailAddress) {
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
  }
  
  public void resetPassword(String key, String password) {
    var passwordReset = getService(PasswordResetService.class).getByKey(key);
    if (passwordReset.isEmpty()) {
        throw new InvalidPasswordReset(key);
    }
    var user = passwordReset.get().getUser();
    user.setPassword(bcryptPasswordEncoder.encode(password));
    repo.save(user);
  }
  
  public void createVerification(String mailAddress) {
    var result = repo.findOne(predicate.withLoginName(mailAddress));
    
    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exist", mailAddress);
    }
    
    if (result.get().getRoles().stream().anyMatch(r -> r.getName().equalsIgnoreCase(RoleService.verified))) {
      throw new AlreadyVerifiedException("Already verified");
    }
    
    if (result.get().getVerification() != null) {
      getService(VerificationService.class).deleteById(result.get().getVerification().getId());
    }
    
    var copy = ReflectionUtils.copy(result.get());
    copy.setVerification(new VerificationEntity());
    persist(result.get(), copy, createContext("verification"));
  }

  public UserEntity verify(String key) {
    var verification = getService(VerificationService.class).getByKey(key);
    if (verification.isPresent()) {
      var user = verification.get().getUser();
      user.getRoles().add(roleService.getVerifiedRole());
      getService(VerificationService.class).deleteById(verification.get().getId());
      return repo.save(user);
    }
    throw new InvalidVerificationException(key);
  }
  
}
