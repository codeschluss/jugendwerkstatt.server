package app.wooportal.server.components.location.bingMaps.model.address;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GeocodePoint {

  private String type;
  private List<Double> coordinates;
  private String calculationMethod;
  private List<String> usageTypes;
  
}
