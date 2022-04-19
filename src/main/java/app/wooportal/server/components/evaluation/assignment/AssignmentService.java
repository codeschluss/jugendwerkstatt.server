package app.wooportal.server.components.evaluation.assignment;

import org.springframework.stereotype.Service;
import app.wooportal.server.components.evaluation.answer.AnswerService;
import app.wooportal.server.components.evaluation.assignmentstate.AssignmentStateService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AssignmentService extends DataService<AssignmentEntity, AssignmentPredicateBuilder> {

  public AssignmentService(DataRepository<AssignmentEntity> repo,
      AssignmentPredicateBuilder predicate, AnswerService answerService,
      AssignmentStateService assignmentStateService) {
    super(repo, predicate);

    addService("answers", answerService);
    addService("assignmentState", assignmentStateService);
  }
}
