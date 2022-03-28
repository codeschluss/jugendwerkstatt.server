package app.wooportal.server.core.error;

import static org.springframework.http.ResponseEntity.noContent;
import app.wooportal.server.core.security.permissions.Authenticated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

  private final ErrorService errorService;
  
  public ErrorController(ErrorService errorService) {
    this.errorService = errorService;
  }
  
  @PostMapping("/error")
  @Authenticated
  public ResponseEntity<?> error(@RequestBody String stackTrace) {
    errorService.sendErrorMail(stackTrace);
    return noContent().build();
  }
  
}