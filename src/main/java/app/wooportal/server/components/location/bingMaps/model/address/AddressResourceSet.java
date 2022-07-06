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
public class AddressResourceSet {

  private Integer estimatedTotal;
  private List<AddressResource> resources;
  
}
