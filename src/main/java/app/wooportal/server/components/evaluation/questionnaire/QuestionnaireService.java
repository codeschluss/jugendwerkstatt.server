package app.wooportal.server.components.evaluation.questionnaire;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.evaluation.question.QuestionService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class QuestionnaireService
    extends DataService<QuestionnaireEntity, QuestionnairePredicateBuilder> {

  public QuestionnaireService(DataRepository<QuestionnaireEntity> repo,
      QuestionnairePredicateBuilder predicate, QuestionService questionService) {
    super(repo, predicate);
    
    addService("questions", questionService);
  }

  @Override
  public Optional<QuestionnaireEntity> getExisting(QuestionnaireEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<QuestionnaireEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}

