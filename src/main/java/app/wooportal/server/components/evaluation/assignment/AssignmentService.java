package app.wooportal.server.components.evaluation.assignment;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.evaluation.answer.AnswerService;
import app.wooportal.server.components.evaluation.assignmentState.AssignmentStateService;
import app.wooportal.server.components.group.feedback.FeedbackEntity;
import app.wooportal.server.components.group.feedback.FeedbackService;
import app.wooportal.server.components.messaging.message.MessageEntity;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class AssignmentService extends DataService<AssignmentEntity, AssignmentPredicateBuilder> {

  private final UserService userService;
  private final PushService pushService;
  private final FeedbackService feedbackService;
  
  public AssignmentService(
      DataRepository<AssignmentEntity> repo,
      AssignmentPredicateBuilder predicate,
      AnswerService answerService,
      AssignmentStateService assignmentStateService,
      UserService userService,
      PushService pushService,
      FeedbackService feedbackService) {
    super(repo, predicate);

    addService("answers", answerService);
    addService("assignmentState", assignmentStateService);
    this.userService = userService;
    this.pushService = pushService;
    this.feedbackService = feedbackService;
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
    if (user != null) {
      var message = new MessageDto("Hat dir der Kurs gefallen?",
          "Bitte bearbeite den Bewertungsbogen!", NotificationType.evaluation);
      pushService.sendPush(user, message);
      
      var feedback = new FeedbackEntity();
      feedback.setUser(user);
      feedback.setRating(null);
      feedback.setCourse(user.getCourse());
      feedbackService.save(feedback);
      
      pushService.sendPush(user, message);
    }
  }
}
