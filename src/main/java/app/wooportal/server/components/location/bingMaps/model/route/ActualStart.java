
package app.wooportal.server.components.location.bingMaps.model.route;

import java.util.List;
import lombok.Data;

@Data
public class ActualStart {

  private String type;
  private List<Double> coordinates;
}
