package app.wooportal.server.components.pushNotifications;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.event.schedule.ScheduleEntity;
import app.wooportal.server.components.event.schedule.ScheduleService;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.components.jobad.base.JobAdEntity;
import app.wooportal.server.components.jobad.base.JobAdService;
import app.wooportal.server.components.subscription.base.SubscriptionEntity;
import app.wooportal.server.components.subscription.base.SubscriptionService;
import app.wooportal.server.core.security.components.user.UserEntity;
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

    var schedules = scheduleService.withDates(List.of(OffsetDateTime.now(),
        OffsetDateTime.now().minusDays(3), OffsetDateTime.now().minusDays(2)), "event");

    Map<String, String> additionalData = new HashMap<>();

    for (ScheduleEntity schedule : schedules) {

      MessageDto message = new MessageDto(
          "Erinnerung zum Event " + schedule.getEvent().getName() + ".",
          "" + schedule.getEvent().getName() + " findet am " + schedule.getStartDate() + " statt.");

      List<SubscriptionEntity> subList = subscriptionService.GetAllSubscriptions();
      for (SubscriptionEntity subscription : subList) {

        firebasePushService.sendPush(subscription, message, additionalData);
      }
    }
  }


  @Scheduled(cron = "0 7 * * * ?")
  public void pushForJobAds() {

    var jobAds = jobAdService.withDueDates(OffsetDateTime.now().minusDays(3),
        OffsetDateTime.now().minusDays(7), OffsetDateTime.now().minusDays(14));

    Map<String, String> additionalData = new HashMap<>();

    for (JobAdEntity jobAd : jobAds) {

      MessageDto message = new MessageDto("Erinnerung zum Jobangebot" + jobAd.getTitle() + ".",
          "Die Bewerbungsfrist endet am" + jobAd.getDueDate() + ".");

      List<SubscriptionEntity> subList = subscriptionService.GetAllSubscriptions();
      for (SubscriptionEntity subscription : subList) {

        firebasePushService.sendPush(subscription, message, additionalData);
      }
    }
  }

  @Scheduled(cron = "0 0 12 * * ?")
  public void pushForEvaluation() {

    List<UserEntity> tempList = userService.Evaluation("subscriptions");

    Map<String, String> additionalData = new HashMap<>();
    MessageDto message =
        new MessageDto("Hat dir der Kurs gefallen?", "Bitte bearbeite den Bewertungsbogen!");

    for (UserEntity user : tempList) {

      Set<SubscriptionEntity> sublist = user.getSubscriptions();

      for (SubscriptionEntity subscription : sublist) {

        firebasePushService.sendPush(subscription, message, additionalData);
      }
    }
  }
}
