package app.wooportal.server.core.security.components.user;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;

@Service
public class UserService extends DataService<UserEntity, UserPredicateBuilder> {

  private final BCryptPasswordEncoder bcryptPasswordEncoder;

  public UserService(
      UserRepository repo,
      BCryptPasswordEncoder encoder) {
    super(repo, null);
    this.bcryptPasswordEncoder = encoder;
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
  
  @Override
  public void preSave(
      UserEntity entity,
      UserEntity newEntity, 
      JsonNode context) {
    if (newEntity.getPassword() != null) {
      entity.setPassword(bcryptPasswordEncoder.encode(newEntity.getPassword()));
      removeContext("password", context);
    }
  }
  
}
