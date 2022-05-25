package app.wooportal.server.components.push;

import java.io.IOException;
import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.event.schedule.ScheduleService;
import app.wooportal.server.components.jobad.base.JobAdService;
import app.wooportal.server.components.push.notification.NotificationEntity;
import app.wooportal.server.components.push.notification.NotificationService;
import app.wooportal.server.components.push.subscription.SubscriptionEntity;
import app.wooportal.server.components.push.subscription.SubscriptionService;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class PushService {

  private final SubscriptionService subscriptionService;
  private final FirebasePushService firebasePushService;
  private final ScheduleService scheduleService;
  private final JobAdService jobAdService;
  private final UserService userService;
  private final NotificationService notificationService;


  public PushService(SubscriptionService subscriptionService,
      FirebasePushService firebasePushService, ScheduleService scheduleService,
      JobAdService jobAdService, UserService userService, NotificationService notificationService)
      throws IOException {
    this.subscriptionService = subscriptionService;
    this.firebasePushService = firebasePushService;
    this.scheduleService = scheduleService;
    this.jobAdService = jobAdService;
    this.userService = userService;
    this.notificationService = notificationService;

  }

  public void sendGlobalPush(MessageDto message) {
    sendPush(message, subscriptionService.getAllSubscriptions());
  }

  public void pushForEvaluation() {
    var message =
        new MessageDto("Hat dir der Kurs gefallen?", "Bitte bearbeite den Bewertungsbogen!");

    for (var student : userService.getAllStudents("subscriptions")) {
      sendPush(message, student.getSubscriptions());
    }
  }

  public void pushForJobAds() {
    for (var jobAd : jobAdService.withDueDates(OffsetDateTime.now().minusDays(3),
        OffsetDateTime.now().minusDays(7), OffsetDateTime.now().minusDays(14))) {
      var message = new MessageDto("Erinnerung zum Jobangebot",
          MessageFormat.format("Die Bewerbungsfrist für {0} endet am {1}.", jobAd.getTitle(),
              jobAd.getDueDate().format(DateTimeFormatter.ofPattern("dd.MM.yyy"))));

      sendPush(message, subscriptionService.getAllSubscriptions());
    }
  }

  public void pushForEvents() {
    for (var schedule : scheduleService.withDates(List.of(OffsetDateTime.now(),
        OffsetDateTime.now().minusDays(3), OffsetDateTime.now().minusDays(2)), "event")) {
      var message = new MessageDto("Erinnerung zum Event",
          MessageFormat.format("{0} findet am {1} statt.", schedule.getEvent().getName(),
              schedule.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM um HH:mm Uhr"))));
     
      sendPush(message, subscriptionService.getAllSubscriptions());
    }
  }
  
  private void sendPush(MessageDto message, Collection<SubscriptionEntity> subscriptions) {
    for (var subscription : subscriptions) {
      var notification = new NotificationEntity();
      notification.setTitle(message.getTitle());
      notification.setContent(message.getContent());
      notification.setUser(subscription.getUser());
      notification.setRead(false);
      notificationService.save(notification);
      
      firebasePushService.sendPush(subscription, message, new HashMap<String, String>());
    }
  }
}
