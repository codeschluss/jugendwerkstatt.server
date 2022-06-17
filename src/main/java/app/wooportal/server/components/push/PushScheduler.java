package app.wooportal.server.components.push;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.event.schedule.ScheduleService;
import app.wooportal.server.components.group.base.GroupService;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.components.group.feedback.FeedbackEntity;
import app.wooportal.server.components.jobad.base.JobAdService;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;

@Component
public class PushScheduler {

  private final PushService pushService;
  private final ScheduleService scheduleService;
  private final JobAdService jobAdService;
  private final UserService userService;
  private final CourseService courseService;
  private final GroupService groupService;

  public PushScheduler(PushService pushService,
      ScheduleService scheduleService,
      JobAdService jobAdService, 
      UserService userService,
      CourseService courseService,
      GroupService groupService) {

    this.pushService = pushService;
    this.scheduleService = scheduleService;
    this.jobAdService = jobAdService;
    this.userService = userService;
    this.courseService = courseService;
    this.groupService = groupService;
  }

  @Scheduled(cron = "0 0 7 * * ?")
  public void pushForEvents() {
    for (var schedule : scheduleService.withDates(List.of(OffsetDateTime.now(),
        OffsetDateTime.now().minusDays(3), OffsetDateTime.now().minusDays(2)), "event")) {
      var message = new MessageDto("Erinnerung zum Event",
          MessageFormat.format("{0} findet am {1} statt.", schedule.getEvent().getName(),
              schedule.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM um HH:mm Uhr"))),
          Map.of(NotificationType.event.toString(), schedule.getEvent().getId()),
          NotificationType.event);

      pushService.sendPush(userService.getRepo().findAll(), message);
    }
  }

  @Scheduled(cron = "0 0 12 * * ?")
  public void pushForJobAds() {
    for (var jobAd : jobAdService.withDueDates(OffsetDateTime.now().minusDays(3),
        OffsetDateTime.now().minusDays(7), OffsetDateTime.now().minusDays(14))) {
      var message = new MessageDto("Erinnerung zum Jobangebot",
          MessageFormat.format("Die Bewerbungsfrist für {0} endet am {1}.", jobAd.getTitle(),
              jobAd.getDueDate().format(DateTimeFormatter.ofPattern("dd.MM.yyy"))),
          Map.of(NotificationType.jobAd.toString(), jobAd.getId()),
          NotificationType.jobAd);

      var users =
          userService.readAll(userService.query()
              .addGraph(userService.graph("subscriptions"))
              .and(userService.getPredicate().withStudentRole())).getList();

      pushService.sendPush(users, message);
    }
  }
<<<<<<< Upstream, based on main
<<<<<<< Upstream, based on main
  
  @Scheduled(cron = "0 0 10 25 * ?")
=======

  //  @Scheduled(cron = "0 0 10 25 * ?")
=======
>>>>>>> 60c3f50 245,248,257
  
<<<<<<< Upstream, based on main
  @Scheduled(cron = "* * * * * ?")
>>>>>>> c09ab6a #257, 246, 248
=======
  @Scheduled(cron = "0 0 10 25 * ?")
>>>>>>> 60c3f50 245,248,257
  public void pushForEvaluation() {
    var message =
        new MessageDto("Hat dir der Kurs gefallen?", "Bitte bearbeite den Bewertungsbogen!",
<<<<<<< Upstream, based on main
            Map.of(NotificationType.evaluation.toString(), "evaluation"),
            NotificationType.evaluation);
=======
            Map.of(NotificationType.evaluation.toString(), "evaluation"));
>>>>>>> c09ab6a #257, 246, 248

    var users = userService
        .readAll(userService.query().addGraph(userService.graph("subscriptions", "groups"))         
            .and(userService.getPredicate().withStudentRole()
            .and(userService.getPredicate().withGroupNotNull()))).getList();

    createFeedbacks(users);
    groupService.updateActiveOrder();
    pushService.sendPush(users, message);
  }
  
  public void createFeedbacks(List<UserEntity> users) {
    for (var user : users) {
<<<<<<< Upstream, based on main
      var courses =
          courseService
              .readAll(courseService.query().addGraph(courseService.graph("groups", "users"))
                  .and(courseService.getPredicate().isActive()
                  .and(courseService.getPredicate().withGroupId(user.getGroup().getId()))))
              .getList();

      if (!courses.isEmpty()) {
        var feedback = new FeedbackEntity();
        feedback.setUser(user);
        feedback.setRating(null);
        feedback.setCourse(courses.get(0)); 
=======

      var courses =
          courseService
              .readAll(courseService.query().addGraph(courseService.graph("groups", "users"))
                  .and(courseService.getPredicate().isActive()
                  .and(courseService.getPredicate().withGroupId(user.getGroup().getId()))))
              .getList();

      if (!courses.isEmpty()) {
      var feedback = new FeedbackEntity();
      feedback.setUser(user);
      feedback.setRating(null);
      feedback.setCourse(courses.get(0)); 
>>>>>>> c09ab6a #257, 246, 248
      }
    }
  }
}
