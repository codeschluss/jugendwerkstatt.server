
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
public class ActualStart {

  private String type;
  private List<Double> coordinates;
}
