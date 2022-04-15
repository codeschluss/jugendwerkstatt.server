package app.wooportal.server.components.documents.templateType;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class TemplateTypeService
    extends DataService<TemplateTypeEntity, TemplateTypePredicateBuilder> {

  public TemplateTypeService(
      DataRepository<TemplateTypeEntity> repo, 
      TemplateTypePredicateBuilder predicate) {
    super(repo, predicate);
  }

}
