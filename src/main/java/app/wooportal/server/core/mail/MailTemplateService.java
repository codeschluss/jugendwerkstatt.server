package app.wooportal.server.core.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
public class MailTemplateService {

  private final Configuration freemarkerConfig;

  private final MailConfiguration mailConfig;

  public MailTemplateService(Configuration freemarkerConfig, MailConfiguration mailConfig) {
    this.freemarkerConfig = freemarkerConfig;
    this.mailConfig = mailConfig;

    this.freemarkerConfig.setClassForTemplateLoading(this.getClass(),
        this.mailConfig.getTemplateLocation());
  }

  public String createMessage(String templateName, Map<String, String> model)
      throws TemplateException, IOException {
    Template t = freemarkerConfig.getTemplate(templateName);
    return FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
  }

}
