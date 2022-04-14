package app.wooportal.server.components.link.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class LinkService extends DataService<LinkEntity, LinkPredicateBuilder> {

  public LinkService(LinkRepository repo, LinkPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
}
