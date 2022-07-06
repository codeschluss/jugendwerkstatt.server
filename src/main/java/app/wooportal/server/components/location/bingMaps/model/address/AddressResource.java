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
public class AddressResource {

  private String type;
  private List<Double> bbox;
  private String name;
  private Point point;
  private Address address;
  private String confidence;
  private String entityType;
  private List<GeocodePoint> geocodePoints;
  private List<String> matchCodes;
}
