
package app.wooportal.server.components.location.bingMaps.model.route;

import java.util.List;
import lombok.Data;

@Data
public class Detail {

  private Integer compassDegrees;
  private List<Integer> endPathIndices;
  private String maneuverType;
  private String mode;
  private List<String> names;
  private String roadType;
  private List<Integer> startPathIndices;
  private List<String> locationCodes;

}
