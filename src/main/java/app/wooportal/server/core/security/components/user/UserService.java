package app.wooportal.server.core.security.components.user;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.AlreadyVerifiedException;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.error.exception.VerificationInvalidException;
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
      VerificationService verificationService) {
    super(repo, predicate);
    
    this.bcryptPasswordEncoder = encoder;
    this.roleService = roleService;
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
  
  public List<UserEntity> getNotVerifiedOlderThan(OffsetDateTime date) {
    return repo.findAll(query(false)
        .and(predicate.createdBeforeAndNotVerified(date)))
        .getList();
  }
  
  @Override
  public void preSave(
      UserEntity entity,
      UserEntity newEntity, 
      JsonNode context) {
    if (newEntity.getPassword() != null) {
      var password = bcryptPasswordEncoder.encode(newEntity.getPassword());
      entity.setPassword(password);
      newEntity.setPassword(password);
      setContext("password", context);
    }
    
    if (entity.getId() == null || entity.getId().isBlank()) {
      newEntity.setVerification(new VerificationEntity());
    }
  }
  
  public void createVerification(String mailAddress) {
    var result = repo.findOne(predicate.withLoginName(mailAddress));
    
    if (result.isEmpty()) {
      throw new NotFoundException("User with mail does not exsit", mailAddress);
    }
    
    if (result.get().getRoles().stream().anyMatch(r -> r.getName().equalsIgnoreCase(RoleService.verified))) {
      throw new AlreadyVerifiedException("Already verified");
    }
    
    if (result.get().getVerification() != null) {
      getService(VerificationService.class).deleteById(result.get().getVerification().getId());
    }
    var newEntity = ReflectionUtils.copy(result.get());
    newEntity.setVerification(new VerificationEntity());
    persist(result.get(), newEntity, null);
  }

  public UserEntity verify(String key) {
    var verification = getService(VerificationService.class).getByKey(key);
    if (verification.isPresent()) {
      var user = verification.get().getUser();
      user.getRoles().add(roleService.getVerifiedRole());
      getService(VerificationService.class).deleteById(verification.get().getId());
      return repo.save(user);
    }
    throw new VerificationInvalidException(key);
  }
  
}
