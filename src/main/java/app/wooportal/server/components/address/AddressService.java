package app.wooportal.server.components.address;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class AddressService extends DataService<AddressEntity, AddressPredicateBuilder> {



  public AddressService(AddressRepository repo, AddressPredicateBuilder predicate) {
    super(repo, predicate);



  }
}
