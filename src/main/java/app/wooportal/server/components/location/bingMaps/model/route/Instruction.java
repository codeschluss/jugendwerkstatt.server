
package app.wooportal.server.components.location.bingMaps.model.route;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Instruction {

  private Object formattedText;
  private String maneuverType;
  private String text;

}
