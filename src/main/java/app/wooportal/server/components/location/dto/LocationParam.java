package app.wooportal.server.components.location.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationParam {
  
  private TravelMode travelMode;
  
  private Coordinate startPoint;
  
  private Coordinate targetPoint;
  
  private List<Coordinate> via;
 
}
