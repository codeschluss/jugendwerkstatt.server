package app.wooportal.server.components.documents.usertemplate;

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
  
}
