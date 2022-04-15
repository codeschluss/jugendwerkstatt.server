package app.wooportal.server.components.documents.templateType;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class TemplateTypeService
    extends DataService<TemplateTypeEntity, TemplateTypePredicateBuilder> {

  public TemplateTypeService(TemplateTypeRepository repo, TemplateTypePredicateBuilder predicate) {
    super(repo, predicate);
  }

}
