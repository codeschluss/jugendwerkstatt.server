package app.wooportal.server.test.units.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import app.wooportal.server.core.config.GeneralConfiguration;
import app.wooportal.server.core.mail.MailConfiguration;
import app.wooportal.server.core.mail.MailService;
import app.wooportal.server.core.mail.MailTemplateService;
import freemarker.template.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestMailService {

  private GeneralConfiguration generalConfig;
  
  private MailConfiguration mailConfig;
  
  private MailService service;
  
  private JavaMailSender mailSenderMock;
  
  public TestMailService() {
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
    service = new MailService(
        generalConfig,
        mailConfig,
        mailSenderMock, 
        new MailTemplateService(
            new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS), 
            mailConfig));
  }
}
