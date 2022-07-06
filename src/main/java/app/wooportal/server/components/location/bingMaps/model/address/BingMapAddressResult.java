package app.wooportal.server.components.location.bingMaps.model.address;

import java.util.List;
import app.wooportal.server.components.location.bingMaps.model.BingMapResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class BingMapAddressResult extends BingMapResult {

  private List<AddressResourceSet> resourceSets;
}
