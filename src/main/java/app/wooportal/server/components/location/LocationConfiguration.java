package app.wooportal.server.components.location;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "location")
public class LocationConfiguration {
  
  private String serviceSubscriptionKey;
  private String addressUrl;
  private String routesUrl;

}
