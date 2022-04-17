
package app.wooportal.server.components.location.bingMaps.model.route;

import java.util.List;
import lombok.Data;

@Data
public class RouteResourceSet {

  private Integer estimatedTotal;
  private List<RouteResource> resources;

}
