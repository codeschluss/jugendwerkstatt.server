package app.wooportal.server.components.jobad.company;

import java.util.Optional;

import org.springframework.stereotype.Service;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class CompanyService extends DataService<CompanyEntity, CompanyPredicateBuilder> {

  public CompanyService(
      DataRepository<CompanyEntity> repo, 
      CompanyPredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<CompanyEntity> getExisting(CompanyEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<CompanyEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}
