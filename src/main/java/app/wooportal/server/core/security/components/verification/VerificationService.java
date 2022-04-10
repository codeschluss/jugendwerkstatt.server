package app.wooportal.server.core.security.components.verification;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.core.base.DataService;
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
      while(true) {
        var key = StringUtils.randomAlphanumeric(255);
        if (getByKey(key).isEmpty()) {
          newEntity.setKey(key);
          setContext("key", context);
          break;
        }
      }
    }
  }
  
}
