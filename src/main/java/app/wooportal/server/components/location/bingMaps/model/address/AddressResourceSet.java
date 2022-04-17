package app.wooportal.server.components.location.bingMaps.model.address;

import java.util.List;
import lombok.Data;

@Data
public class AddressResourceSet {

  private Integer estimatedTotal;
  private List<AddressResource> resources;
  
}
