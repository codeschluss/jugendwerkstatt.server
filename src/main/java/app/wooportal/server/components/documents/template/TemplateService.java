package app.wooportal.server.components.documents.template;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class TemplateService extends DataService<TemplateEntity, TemplatePredicateBuilder> {

  public TemplateService(DataRepository<TemplateEntity> repo, TemplatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
