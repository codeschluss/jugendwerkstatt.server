package app.wooportal.server.components.group.course;

import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.group.feedback.FeedbackEntity;
import app.wooportal.server.components.group.feedback.FeedbackService;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.components.push.MessageDto;
import app.wooportal.server.components.push.NotificationType;
import app.wooportal.server.components.push.PushService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class CourseService extends DataService<CourseEntity, CoursePredicateBuilder> {

  private final FeedbackService feedbackService;
  private final UserService userService;
  private final ParticipantService participantService;
  private final PushService pushService;

  public CourseService(DataRepository<CourseEntity> repo, CoursePredicateBuilder predicate,
      FeedbackService feedbackService, UserService userService,
      ParticipantService participantService, PushService pushService) {
    super(repo, predicate);

    this.feedbackService = feedbackService;
    this.userService = userService;
    this.participantService = participantService;
    this.pushService = pushService;
    
  }

  @Override
  public Optional<CourseEntity> getExisting(CourseEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<CourseEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }

  public double calculateAverageRating(CourseEntity course, Integer year) {
    var feedbacks = feedbackService
        .readAll(feedbackService.query().and(feedbackService.getPredicate().withYear(year))
            .and(feedbackService.getPredicate().withCourseId(course.getId())))
        .getList();

    var sum = 0.0;
    for (var feedback : feedbacks) {
      if (feedback != null) {
        sum += feedback.getRating().doubleValue();
      } else {
        feedbacks.remove(feedback);
      }
    }

    return feedbacks != null && feedbacks.size() > 0 ? sum / feedbacks.size() : sum;
  }

  public boolean addMember(String courseId, String userId) {
    var course = getById(courseId);
    var user = userService.getById(userId);

    if (course.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("group or user does not exist", courseId);
    }
    createFeedback(user, user.get().getCourse());

    participantService
        .deleteAll(participantService
            .readAll(participantService.query()
                .and(participantService.getPredicate().withUser(user.get().getId()).and(
                    participantService.getPredicate().withCourse(courseId))))
            .getList());

    user.get().setCourse(course.get());
    userService.save(user.get());

    var participant = new ParticipantEntity();
    participant.setChat(course.get().getGroup().getChat());
    participant.setUser(userService.getById(userId).get());
    participantService.save(participant);

    return true;
  }

  public boolean deleteMember(String courseId, String userId) {
    var course = getById(courseId);
    var user = userService.getById(userId);

    if (course.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("group or user does not exist", courseId);
    }
    user.get().setCourse(null);
    userService.save(user.get());

    var participants =
        participantService
            .readAll(
                participantService.query()
                    .addGraph(participantService.graph("users", "chats", "groups"))
                    .and(participantService.getPredicate().withUser(userId)).and(participantService
                        .getPredicate().withChat(course.get().getGroup().getChat().getId())))
            .getList();
    participantService.deleteAll(participants);

    return true;
  }

  public void createFeedback(Optional<UserEntity> user, CourseEntity oldCourse) {

    if (oldCourse != null) {
      var message =
          new MessageDto("Hat dir der Kurs gefallen?", "Bitte bearbeite den Bewertungsbogen!",
              Map.of(NotificationType.evaluation.toString(), "evaluation"),
              NotificationType.evaluation);
      pushService.sendPush(user.get(), message);

      var feedback = new FeedbackEntity();
      feedback.setUser(user.get());
      feedback.setRating(null);
      feedback.setCourse(oldCourse);
      feedbackService.save(feedback);
    }
  }
}


