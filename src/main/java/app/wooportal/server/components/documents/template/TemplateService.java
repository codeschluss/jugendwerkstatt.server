package app.wooportal.server.components.documents.template;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class TemplateService extends DataService<TemplateEntity, TemplatePredicateBuilder> {

  public TemplateService(TemplateRepository repo, TemplatePredicateBuilder predicate) {
    super(repo, predicate);
  }
}
