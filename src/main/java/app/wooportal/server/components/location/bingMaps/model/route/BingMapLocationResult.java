
package app.wooportal.server.components.location.bingMaps.model.route;

import java.util.List;
import app.wooportal.server.components.location.bingMaps.model.BingMapResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BingMapLocationResult extends BingMapResult {
  
  private List<RouteResourceSet> resourceSets;
}
