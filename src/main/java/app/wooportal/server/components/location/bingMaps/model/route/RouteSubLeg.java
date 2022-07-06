
package app.wooportal.server.components.location.bingMaps.model.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteSubLeg {

  private EndWaypoint endWaypoint;
  private StartWaypoint startWaypoint;
  private Double travelDistance;
  private Integer travelDuration;

}
