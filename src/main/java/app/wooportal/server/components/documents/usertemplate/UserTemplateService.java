package app.wooportal.server.components.documents.usertemplate;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class UserTemplateService
    extends DataService<UserTemplateEntity, UserTemplatePredicateBuilder> {

  public UserTemplateService(
      DataRepository<UserTemplateEntity> repo, 
      UserTemplatePredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<UserTemplateEntity> getExisting(UserTemplateEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<UserTemplateEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}
