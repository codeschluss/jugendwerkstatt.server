package app.wooportal.server.core.security.components.verification;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.twelvemonkeys.lang.StringUtil;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.utils.StringUtils;

@Service
public class VerificationService extends DataService<VerificationEntity, VerificationPredicateBuilder> {

  public VerificationService(
      VerificationRepository repo,
      VerificationPredicateBuilder predicate) {
    super(repo, predicate);
  }

  public Optional<VerificationEntity> getByKey(String name) {
    return repo.findOne(predicate.withKey(name));
  }
  
  @Override
  public void preSave(
      VerificationEntity entity,
      VerificationEntity newEntity, 
      JsonNode context) {
    if(newEntity.getKey() == null || newEntity.getKey().isBlank()) {
      newEntity.setKey(StringUtils.randomAlphanumeric(255));
    }
  }
  
}
