package app.wooportal.server.components.event.category;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class CategoryService extends DataService<CategoryEntity, CategoryPredicateBuilder> {

  public CategoryService(CategoryRepository repo, CategoryPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
  @Override
  public Optional<CategoryEntity> getExisting(CategoryEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty()
        ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<CategoryEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}

