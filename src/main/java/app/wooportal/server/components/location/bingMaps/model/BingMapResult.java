package app.wooportal.server.components.location.bingMaps.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BingMapResult {

  protected String authenticationResultCode;
  protected String brandLogoUri;
  protected String copyright;
  protected Integer statusCode;
  protected String statusDescription;
  protected String traceId;
  
}
