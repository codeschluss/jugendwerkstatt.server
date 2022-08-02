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
      if (feedback != null && feedback.getRating() != null) {
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
      throw new BadParamsException("course or user does not exist", courseId);
    }
    createFeedback(user.get());
    deleteOldParticipant(user.get(), course.get());
    createParticipant(user.get(), course.get());
        
    user.get().setCourse(course.get());
    userService.save(user.get());
    return true;
  }

  private void createParticipant(UserEntity user, CourseEntity course) {

    if (course.getGroup().getChat() != null && user.getCourse() != null
        && user.getCourse().getGroup().getId() != course.getGroup().getId()
        || course.getGroup().getChat() != null && user.getCourse() == null) {
      var participant = new ParticipantEntity();
      participant.setChat(course.getGroup().getChat());
      participant.setUser(user);
      participantService.save(participant);
    }
  }

  private void deleteOldParticipant(UserEntity user, CourseEntity course) {

    if (user.getCourse() != null
        && user.getCourse().getGroup().getId() != course.getGroup().getId()) {
      participantService
          .deleteAll(
              participantService
                  .readAll(participantService.query()
                    .and(participantService.getPredicate().withUser(user.getId())
                    .and(participantService.getPredicate().withCourse(user.getCourse().getId()))))
                  .getList());
    }
  }

  public boolean deleteMember(String courseId, String userId) {
    var course = getById(courseId);
    var user = userService.getById(userId);

    if (course.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("group or user does not exist", courseId);
    }
    createFeedback(user.get());
    user.get().setCourse(null);
    userService.save(user.get());

    participantService.deleteAll(participantService
        .readAll(participantService.query()
            .and(participantService.getPredicate().withUser(user.get().getId()).and(
                participantService.getPredicate().withCourse(course.get().getId()))))
        .getList());

    return true;
  }

  public void createFeedback(UserEntity user) {

    if (user.getCourse() != null) {
      var message =
          new MessageDto(
              "Hat dir der Kurs gefallen?",
              "Bitte bearbeite den Bewertungsbogen!",
              NotificationType.evaluation);
      pushService.sendPush(user, message);

      var feedback = new FeedbackEntity();
      feedback.setUser(user);
      feedback.setRating(null);
      feedback.setCourse(user.getCourse());
      feedbackService.save(feedback);
    }
  }
}


