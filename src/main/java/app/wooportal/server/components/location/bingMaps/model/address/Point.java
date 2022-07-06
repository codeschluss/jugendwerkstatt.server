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
public class Point {

  private String type;
  private List<Float> coordinates;

  public float getLatitude() {
    return coordinates.get(0);
  }

  public float getLongitude() {
    return coordinates.get(1);
  }

}
