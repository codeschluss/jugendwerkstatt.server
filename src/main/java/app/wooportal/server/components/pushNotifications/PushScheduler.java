package app.wooportal.server.components.pushNotifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import app.wooportal.server.components.event.base.EventEntity;
import app.wooportal.server.components.event.schedule.ScheduleEntity;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.components.jobad.base.JobAdEntity;
import app.wooportal.server.components.subscription.base.SubscriptionEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;

@Component
public class PushScheduler {

  private final PushService firebasePushService;
  private final UserService userService;



  public PushScheduler(PushService firebasePushService, UserService userService,
      CourseService courseService) {
    this.firebasePushService = firebasePushService;
    this.userService = userService;

  }

  @Scheduled(fixedDelay = 30000)
  public void pushForEvents() {

    List<UserEntity> tempList = userService.GetAllUsers("favoriteEvents.schedules");

    Map<String, String> additionalData = new HashMap<>();

    for (UserEntity user : tempList) {
            
      for (EventEntity event : user.getFavoriteEvents()) {

        OffsetDateTime currentDate = OffsetDateTime.now();
        
        for (ScheduleEntity scheduleList: event.getSchedules()) {
          
        if (currentDate.getDayOfMonth() == (scheduleList.getCreated().minusDays(3).getDayOfMonth())
            || (currentDate.getDayOfMonth() == (scheduleList.getCreated().getDayOfMonth()))) {
          
          MessageDto message =
              new MessageDto("Erinnerung zum Event"+event.getName()+".", ""+event.getName()+"findet am"+scheduleList.getCreated()+"statt.");
          
          Set<SubscriptionEntity> subList = user.getSubscriptions();
          for (SubscriptionEntity subscription : subList) {

            firebasePushService.sendPush(subscription, message, additionalData);
          }
        }
      }
    }
    }
  }

  @Scheduled(fixedDelay = 30000)
  public void pushForJobAds() {

    List<UserEntity> tempList = userService.GetAllUsers("favoriteJobAds");

    Map<String, String> additionalData = new HashMap<>();

    for (UserEntity user : tempList) {

      for (JobAdEntity jobAd : user.getFavoriteJobAds()) {

        OffsetDateTime currentDate = OffsetDateTime.now();

        if (currentDate.getDayOfMonth() == (jobAd.getDueDate().minusDays(14).getDayOfMonth())
            || (currentDate.getDayOfMonth() == (jobAd.getDueDate().minusDays(7).getDayOfMonth()))) {

          MessageDto message = new MessageDto("Erinnerung zum Jobangebot" + jobAd.getTitle() + ".",
              "Die Bewerbungsfrist endet am" + jobAd.getDueDate() + ".");

          Set<SubscriptionEntity> subList = user.getSubscriptions();
          for (SubscriptionEntity subscription : subList) {

            firebasePushService.sendPush(subscription, message, additionalData);
          }
        }
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
