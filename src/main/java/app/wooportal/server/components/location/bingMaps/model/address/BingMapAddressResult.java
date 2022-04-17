package app.wooportal.server.components.location.bingMaps.model.address;

import java.util.List;
import app.wooportal.server.components.location.bingMaps.model.BingMapResult;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class BingMapAddressResult extends BingMapResult {

  private List<AddressResourceSet> resourceSets;
}
