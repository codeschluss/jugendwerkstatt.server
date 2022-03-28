package app.wooportal.server.test.units.core.error;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import javax.mail.MessagingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import app.wooportal.server.core.error.ErrorService;
import app.wooportal.server.core.mail.MailService;

public class ErrorServiceTest {
  
  private static MailService mailServiceMock;
  private static ErrorService errorService;
  
  @BeforeAll
  public static void init() {
    mailServiceMock = mock(MailService.class);
    errorService = new ErrorService(mailServiceMock);
  }
  
  @Test
  public void sendErrorMailWithException() throws MessagingException {   
    var test = new Exception("test");
    
    errorService.sendErrorMail(test);
    
    verify(mailServiceMock).sendEmail(
        argThat(subject -> subject.equals("Error")), 
        argThat(template -> template.equals("error.ftl")), 
        argThat(templateModel -> templateModel.containsKey("error") && templateModel.containsKey("metaInfo")),
        any());
  }

}
