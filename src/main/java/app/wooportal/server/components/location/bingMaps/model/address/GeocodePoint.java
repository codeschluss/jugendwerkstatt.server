package app.wooportal.server.components.location.bingMaps.model.address;

import java.util.List;
import lombok.Data;

@Data
public class GeocodePoint {

  private String type;
  private List<Double> coordinates;
  private String calculationMethod;
  private List<String> usageTypes;
  
}
