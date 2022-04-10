package app.wooportal.server.core.security.components.user;

import java.time.OffsetDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserScheduler {
  
  private final UserService userService;
  
  public UserScheduler(
      UserService userService) {
    this.userService = userService;
  }
  
  @Scheduled(cron = "0 0 0 * * *")
  public void checkNotVerifiedUsers() {
    var notVerified = userService.getNotVerifiedOlderThan( 
        OffsetDateTime.now().minusDays(14));
    
    if (notVerified != null && !notVerified.isEmpty()) {
      userService.deleteAll(notVerified);
    }
  }

}
