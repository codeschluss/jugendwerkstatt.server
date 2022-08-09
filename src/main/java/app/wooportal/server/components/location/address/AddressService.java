package app.wooportal.server.components.location.address;

import java.util.Optional;
import javax.naming.ServiceUnavailableException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.location.bingMaps.BingMapService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.NotFoundException;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class AddressService extends DataService<AddressEntity, AddressPredicateBuilder> {
  
  private final BingMapService bingMapService;

  public AddressService(
      DataRepository<AddressEntity> repo, 
      AddressPredicateBuilder predicate,
      BingMapService bingMapService) {
    super(repo, predicate);
    
    this.bingMapService = bingMapService;
  }
  
  @Override
  public Optional<AddressEntity> getExisting(AddressEntity entity) {
    return repo.findOne(predicate.withFullAddress(entity));
  }
  
  @Override
  protected void preSave(AddressEntity entity, AddressEntity newEntity, JsonNode context) {
    try {
      if (newEntity.getId() == null) {
        var externalAddress = bingMapService.retrieveExternalAddress(newEntity);
        
        var result = getExisting(externalAddress);
        if (result.isPresent()) {
          newEntity.setId(result.get().getId());
        }
        newEntity.setPostalCode(externalAddress.getPostalCode());
        newEntity.setPlace(externalAddress.getPlace());
        newEntity.setHouseNumber(externalAddress.getHouseNumber());
        newEntity.setStreet(externalAddress.getStreet());
        newEntity.setLatitude(externalAddress.getLatitude());
        newEntity.setLongitude(externalAddress.getLongitude());
        
        setContext("longitude", context);
        setContext("latitude", context);
      }
    } catch (ServiceUnavailableException | NotFoundException e) {
      // TODO: what to do when not found?
      e.printStackTrace();
    }
  }

  public AddressEntity lookup(AddressEntity entity) 
      throws ServiceUnavailableException, NotFoundException {
    return bingMapService.retrieveExternalAddress(entity);
  } 
}
