package app.wooportal.server.core.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.config.GeneralConfiguration;

@Service
public class MailService {

  private final GeneralConfiguration generalConfig;
  
  private final MailConfiguration mailConfig;
  
  private final JavaMailSender sender;

  private final MailTemplateService templateService;

  public MailService(
      GeneralConfiguration generalConfig,
      MailConfiguration mailConfig,
      JavaMailSender sender,
      MailTemplateService templateService) {
    this.generalConfig = generalConfig;
    this.mailConfig = mailConfig;
    this.sender = sender;
    this.templateService = templateService;
  }

  @Async
  public CompletableFuture<Boolean> sendEmail(
      String subject, 
      String template,
      Map<String, String> templateModel,
      String... to) {
    try {
      var model = new HashMap<>(templateModel);
      model.put("portalName", generalConfig.getPortalName());
      sendEmail(
          subject, 
          templateService.createMessage(template, model),
          to);
      return CompletableFuture.completedFuture(true);
    } catch (Exception e) {
      e.printStackTrace();
      return CompletableFuture.completedFuture(false);
    }
  }

  public void sendEmail(String subject, String content, String... to)
      throws MessagingException {
    sendEmail(mailConfig.getFromAddress(), subject, content, true, 
        to == null || to.length == 0 ? new String[] {mailConfig.getToAddress()} : to);
  }

  public void sendEmail(String fromAddress, String subject, String content, boolean html,
      String... toAddresses) throws MessagingException {
    subject = "[" + generalConfig.getPortalName() + "] - " + subject;
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setFrom(fromAddress);
    helper.setTo(toAddresses);
    helper.setSubject(subject);
    helper.setText(content, html);
    sender.send(message);
  }
  
}
