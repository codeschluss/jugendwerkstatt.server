package app.wooportal.server.components.link.category;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class LinkCategoryService
    extends DataService<LinkCategoryEntity, LinkCategoryPredicateBuilder> {

  public LinkCategoryService(LinkCategoryRepository repo, LinkCategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<LinkCategoryEntity> getExisting(LinkCategoryEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<LinkCategoryEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}

