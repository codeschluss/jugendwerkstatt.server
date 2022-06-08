package app.wooportal.server.components.push;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.event.schedule.ScheduleService;
import app.wooportal.server.components.jobad.base.JobAdService;
import app.wooportal.server.core.security.components.user.UserService;

@Component
public class PushScheduler {

  private final PushService pushService;
  private final ScheduleService scheduleService;
  private final JobAdService jobAdService;
  private final UserService userService;
  

  public PushScheduler(PushService pushService,
      ScheduleService scheduleService,
      JobAdService jobAdService,
      UserService userService) {
    
    this.pushService = pushService;
    this.scheduleService = scheduleService;
    this.jobAdService = jobAdService;
    this.userService = userService;
    
  }

  @Scheduled(cron = "0 0 7 * * ?")
  public void pushForEvents() {
    for (var schedule : scheduleService.withDates(List.of(OffsetDateTime.now(),
        OffsetDateTime.now().minusDays(3), OffsetDateTime.now().minusDays(2)), "event")) {
      var message = new MessageDto(
          "Erinnerung zum Event",
          MessageFormat.format("{0} findet am {1} statt.", schedule.getEvent().getName(),
              schedule.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM um HH:mm Uhr"))),
          Map.of(NotificationType.event.toString(), schedule.getEvent().getId()));
     
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
          Map.of(NotificationType.jobAd.toString(), jobAd.getId()));

      pushService.sendPush(userService.getRepo().findAll(), message);
    }
  }

  @Scheduled(cron = "0 0 10 25 * ?")
  public void pushForEvaluation() {
    var message =
        new MessageDto(
            "Hat dir der Kurs gefallen?",
            "Bitte bearbeite den Bewertungsbogen!",
            Map.of(NotificationType.evaluation.toString(), "evaluation"));

      pushService.sendPush(userService.getAllStudentsWithCourse("subscriptions"), message);
    }
  }
