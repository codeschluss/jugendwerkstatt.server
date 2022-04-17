
package app.wooportal.server.components.location.bingMaps.model.route;

import java.util.List;
import lombok.Data;

@Data
public class Line {

  public String type;
  public List<List<Double>> coordinates;

}
