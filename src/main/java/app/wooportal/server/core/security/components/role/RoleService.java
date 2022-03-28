package app.wooportal.server.core.security.components.role;

import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class RoleService extends DataService<RoleEntity, RolePredicateBuilder> {
 
  public static String analystRole = "analyst";
  
  public static String adminRole = "admin";
  
  public static String inspectorRole = "inspector";
  
  public RoleService(
      RoleRepository repo) {
    super(repo, null);
  }

  public List<RoleEntity> getByUser(UserEntity user) {
    return repo.findAll(query()
        .and(predicate.withUserId(user.getId()))).getList();
  }

}
