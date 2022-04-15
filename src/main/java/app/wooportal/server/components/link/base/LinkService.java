package app.wooportal.server.components.link.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class LinkService extends DataService<LinkEntity, LinkPredicateBuilder> {

  public LinkService(
      DataRepository<LinkEntity> repo, 
      LinkPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
}
