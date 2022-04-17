package app.wooportal.server.components.location;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * The Class LocationConfiguration.
 * 
 * @author Valmir Etemi
 *
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "location")
public class LocationConfiguration {
  
  private String serviceSubscriptionKey;
  private String addressUrl;
  private String routesUrl;

}
