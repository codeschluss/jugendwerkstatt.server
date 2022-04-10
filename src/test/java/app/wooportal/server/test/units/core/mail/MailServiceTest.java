package app.wooportal.server.test.units.core.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.mail.MailConfiguration;
import app.wooportal.server.core.mail.MailService;
import app.wooportal.server.core.mail.MailTemplateService;
import freemarker.template.Configuration;

public class MailServiceTest {
  
  private static GeneralConfiguration generalConfig;
  
  private static MailConfiguration mailConfig;
  
  private static MailService mailService;
  
  private static JavaMailSender mailSenderMock;
  
  @BeforeAll
  public static void init() {
    mailSenderMock = mock(JavaMailSender.class);
    when(mailSenderMock.createMimeMessage()).thenReturn(
        new MimeMessage(Session.getDefaultInstance(new Properties())));
    
    generalConfig = new GeneralConfiguration(
        "localhost",
        "portal");
    
    mailConfig = new MailConfiguration(
        "test@from.de", 
        "test@to.de", 
        "/templates/");
    mailService = new MailService(
        generalConfig,
        mailConfig,
        mailSenderMock, 
        new MailTemplateService(
            new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS), 
            mailConfig));
  }
  
  @Test
  public void sendEmailWithTemplate() throws MessagingException, InterruptedException, ExecutionException {
    var paramValue1 = "param1";
    var paramValue2 = "param2";
    var subject = "test";
    var params = Map.of(
        "param1", paramValue1,
        "param2", paramValue2);
    var result = mailService.sendEmail(subject, "test.ftl", params);
    
    assertThat(result.get()).isTrue();
    verify(mailSenderMock).send((MimeMessage) argThat((message)-> {
      try {
        assertThat(((MimeMessage) message).getSubject()).contains(subject);
        assertThat(((MimeMessage) message).getSubject()).contains(generalConfig.getPortalName());
        assertThat(((MimeMessage) message).getFrom()).anyMatch(address -> 
          ((InternetAddress) address).getAddress().equals(mailConfig.getFromAddress()));
        assertThat(((MimeMessage) message).getRecipients(Message.RecipientType.TO)).anyMatch(address -> 
          ((InternetAddress) address).getAddress().equals(mailConfig.getToAddress()));
        assertThat((String) ((MimeMessage) message).getContent()).contains(paramValue1, paramValue2);
      } catch (MessagingException | IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      return true;
    }));
  }

}
