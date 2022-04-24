package app.wooportal.server.components.pushNotifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.components.subscription.base.SubscriptionEntity;
import app.wooportal.server.components.subscription.base.SubscriptionService;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;

@Component
public class PushScheduler {

  private final PushService firebasePushService;
  private final UserService userService;
  private final SubscriptionService subscriptionService;



  public PushScheduler(PushService firebasePushService, UserService userService,
      CourseService courseService, SubscriptionService subscriptionService) {
    this.firebasePushService = firebasePushService;
    this.userService = userService;

    this.subscriptionService = subscriptionService;

  }

  // @Scheduled(cron = "* * * * * *")
  // public void pushEveryMinute() {
  // SubscriptionEntity subscription = new SubscriptionEntity();
  // Map<String, String> additionalData = new HashMap<>();
  // MessageDto message = new MessageDto();
  //
  // firebasePushService.sendPush(subscription, message, additionalData);
  // }

  @Scheduled(cron = "* * * * * ?")
  public void pushMonthly() {

    List<UserEntity> tempList = userService.pushtoStudents();

    Map<String, String> additionalData = new HashMap<>();
    MessageDto message = new MessageDto("Did you enjoy your course?", "Please submit your evaluation!");

    for (UserEntity userEntity : tempList) {

      Optional<SubscriptionEntity> subscription =
          subscriptionService.getByUserId(userEntity.getId());

      firebasePushService.sendPush(subscription, message, additionalData);
    }

  }
}
