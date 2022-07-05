package app.wooportal.server.components.messaging.call;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class CallService extends DataService<CallEntity, CallPredicateBuilder> {
  
  

  public CallService(
      DataRepository<CallEntity> repo,
      CallPredicateBuilder predicate) {
    super(repo, predicate);
    
    
  }
}

