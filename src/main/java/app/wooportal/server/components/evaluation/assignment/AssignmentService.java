package app.wooportal.server.components.evaluation.assignment;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.evaluation.answer.AnswerService;
import app.wooportal.server.components.evaluation.assignmentState.AssignmentStateService;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AssignmentService extends DataService<AssignmentEntity, AssignmentPredicateBuilder> {

  private final PushService pushService;

  public AssignmentService(DataRepository<AssignmentEntity> repo,
      AssignmentPredicateBuilder predicate, AnswerService answerService,
      AssignmentStateService assignmentStateService, PushService pushService) {
    super(repo, predicate);

    addService("answers", answerService);
    addService("assignmentState", assignmentStateService);
    this.pushService = pushService;
  }

  @Override
  protected void preSave(AssignmentEntity entity, AssignmentEntity newEntity, JsonNode context) {
    if (newEntity.getAnswers() != null) {
      newEntity.setAssignmentState(getService(AssignmentStateService.class).getDoneState());
      setContext("assignmentState", context);
    } else {
      newEntity.setAssignmentState(getService(AssignmentStateService.class).getAssignedState());
      setContext("assignmentState", context);
    }
  }

  @Override
  protected void postSave(AssignmentEntity saved, AssignmentEntity newEntity, JsonNode context) {
    var user = saved.getUser();
    if (user != null && newEntity.getAssignmentState().getName() == "done") {
      var message = new MessageDto("Hat dir der Kurs gefallen?",
          "Bitte bearbeite den Bewertungsbogen!", NotificationType.evaluation);
      pushService.sendPush(user, message);
    }
  }
}
