package app.wooportal.server.components.location.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {

  private Double latitude;
  
  private Double longitude;

  @Override
  public String toString() {
    return latitude + "," + longitude;
  }
  
  
}
