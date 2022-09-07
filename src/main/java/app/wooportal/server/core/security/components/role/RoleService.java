    package app.wooportal.server.core.security.components.role;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserEntity;

@Service
public class RoleService extends DataService<RoleEntity, RolePredicateBuilder> {
  
  public static String admin = "admin";
  
  public static String student = "student";
  
  public static String supervisor = "supervisor";
  
  public RoleService(
      DataRepository<RoleEntity> repo,
      RolePredicateBuilder predicate) {
    super(repo, predicate);
  }

  public List<RoleEntity> getByUser(UserEntity user) {
    return repo.findAll(query()
        .and(predicate.withUserId(user.getId()))).getList();
  }

  public RoleEntity getAdminRole() {
    return repo.findOne(predicate.withKey(admin)).get();
  }
  
  public RoleEntity getStudentRole() {
    return repo.findOne(predicate.withKey(student)).get();
  }
  
  @PostConstruct
  public void checkRoles() {
    if (!repo.exists(predicate.withKey(admin))
        || !repo.exists(predicate.withKey(student))) {
      throw new NotFoundException("Not all roles exists!");
    }
  }

}
