
package app.wooportal.server.components.location.bingMaps.model.route;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteResource {

  public String type;
  public List<Double> bbox;
  public String id;
  public String distanceUnit;
  public String durationUnit;
  public List<RouteLeg> routeLegs;
  public RoutePath routePath;
  public String trafficCongestion;
  public String trafficDataUsed;
  public Double travelDistance;
  public Integer travelDuration;
  public Integer travelDurationTraffic;
  public String travelMode;

}
