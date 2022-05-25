package app.wooportal.server.components.push;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PushScheduler {

  private final PushService pushService;

  public PushScheduler(PushService pushService) {
    this.pushService = pushService;
  }

  @Scheduled(cron = "0 0 7 * * ?")
  public void pushInTheMorning() {
    pushService.pushForEvents();
  }

  @Scheduled(cron = "0 0 12 * * ?")
  public void pushNoon() {
    pushService.pushForJobAds();
  }

  @Scheduled(cron = "0 0 10 25 * ?")
  public void pushMonthly() {
    pushService.pushForEvaluation();
  }
}
