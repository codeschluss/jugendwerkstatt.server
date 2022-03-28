package app.wooportal.server.core.mail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import freemarker.template.TemplateException;

@Service
public class MailService {

  private final JavaMailSender sender;

  private final MailConfiguration mailConfig;

  private final MailTemplateService templateService;

  public MailService(
      JavaMailSender sender, 
      MailConfiguration mailConfig,
      MailTemplateService templateService) {
    this.sender = sender;
    this.mailConfig = mailConfig;
    this.templateService = templateService;
  }

  @Async()
  public CompletableFuture<Boolean> sendEmail(
      String subject, 
      String template,
      Map<String, String> templateModel,
      String... to) {
    try {
      var model = new HashMap<>(templateModel);
      model.put("portalName", mailConfig.getPortalName());
      sendEmail(
          subject, 
          templateService.createMessage(template, model),
          to);
      return CompletableFuture.completedFuture(true);
    } catch (TemplateException | IOException | MessagingException e) {
      e.printStackTrace();
      return CompletableFuture.completedFuture(false);
    }
  }

  /**
   * Send email.
   *
   * @param subject the subject
   * @param content the content
   * @param to the to
   * @throws MessagingException the messaging exception
   */
  public void sendEmail(String subject, String content, String... to)
      throws MessagingException {
    sendEmail(mailConfig.getFromAddress(), subject, content, true, 
        to == null || to.length == 0 ? new String[] {mailConfig.getToAddress()} : to);
  }

  /**
   * Send email.
   *
   * @param fromAddress the from address
   * @param subject the subject
   * @param content the content
   * @param html the html
   * @param toAddresses the to addresses
   * @throws MessagingException the messaging exception
   */
  public void sendEmail(String fromAddress, String subject, String content, boolean html,
      String... toAddresses) throws MessagingException {
    subject = "[" + mailConfig.getPortalName() + "] - " + subject;
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setFrom(fromAddress);
    helper.setTo(toAddresses);
    helper.setSubject(subject);
    helper.setText(content, html);
    sender.send(message);
  }
  
}
