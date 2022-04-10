package app.wooportal.server.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Configuration
@ConfigurationProperties(prefix = "general")
@AllArgsConstructor
@NoArgsConstructor
public class GeneralConfiguration {
  
  private String host;
  
  private String portalName;
  
}
