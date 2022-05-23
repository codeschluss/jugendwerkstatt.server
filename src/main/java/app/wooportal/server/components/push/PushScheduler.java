package app.wooportal.server.components.push;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.event.schedule.ScheduleService;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.components.jobad.base.JobAdService;
import app.wooportal.server.components.subscription.base.SubscriptionEntity;
import app.wooportal.server.components.subscription.base.SubscriptionService;
import app.wooportal.server.core.security.components.user.UserService;

@Component
public class PushScheduler {

  private final PushService firebasePushService;
  private final UserService userService;
  private final ScheduleService scheduleService;
  private final SubscriptionService subscriptionService;
  private final JobAdService jobAdService;

  public PushScheduler(ScheduleService scheduleService, PushService firebasePushService,
      UserService userService, CourseService courseService, SubscriptionService subscriptionService,
      JobAdService jobAdService) {
    this.scheduleService = scheduleService;
    this.subscriptionService = subscriptionService;
    this.firebasePushService = firebasePushService;
    this.userService = userService;
    this.jobAdService = jobAdService;
  }

  @Scheduled(cron = "0 7 * * * ?")
  public void pushForEvents() {
    for (var schedule : scheduleService.withDates(List.of(
        OffsetDateTime.now(),
        OffsetDateTime.now().minusDays(3),
        OffsetDateTime.now().minusDays(2)), "event")) {
      var message = new MessageDto(
          "Erinnerung zum Event",
          MessageFormat.format("{0} findet am {1} statt.", 
              schedule.getEvent().getName(), schedule.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM um HH:mm Uhr"))));
      
      for (var subscription : subscriptionService.getAllSubscriptions()) {
        firebasePushService.sendPush(subscription, message, new HashMap<String, String>());
      }
    }
  }

  @Scheduled(cron = "0 7 * * * ?")
  public void pushForJobAds() {
    for (var jobAd : jobAdService.withDueDates(OffsetDateTime.now().minusDays(3),
        OffsetDateTime.now().minusDays(7), OffsetDateTime.now().minusDays(14))) {
      var message = new MessageDto(
          "Erinnerung zum Jobangebot",
          MessageFormat.format("Die Bewerbungsfrist für {0} endet am {1}.", jobAd.getTitle(), jobAd.getDueDate()));

      List<SubscriptionEntity> subList = subscriptionService.getAllSubscriptions();
      for (SubscriptionEntity subscription : subList) {
        firebasePushService.sendPush(subscription, message, new HashMap<String, String>());
      }
    }
  }

  @Scheduled(cron = "0 0 1 * * ?")
  public void pushForEvaluation() {
    var message =
        new MessageDto(
            "Hat dir der Kurs gefallen?",
            "Bitte bearbeite den Bewertungsbogen!");

    for (var student : userService.getAllStudents("subscriptions")) {
      for (var subscription : student.getSubscriptions()) {
        firebasePushService.sendPush(subscription, message, new HashMap<String, String>());
      }
    }
  }
}
