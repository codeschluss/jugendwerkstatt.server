package app.wooportal.server.components.link.base;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class LinkService extends DataService<LinkEntity, LinkPredicateBuilder> {

  public LinkService(LinkRepository repo, LinkPredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<LinkEntity> getExisting(LinkEntity entity) {
    return entity.getTitle() == null || entity.getTitle().isEmpty() ? Optional.empty()
        : getByTitle(entity.getTitle());
  }

  public Optional<LinkEntity> getByTitle(String name) {
    return repo.findOne(predicate.withTitle(name));
  }
}
