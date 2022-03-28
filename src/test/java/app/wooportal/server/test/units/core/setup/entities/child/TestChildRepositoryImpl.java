package app.wooportal.server.test.units.core.setup.entities.child;

import java.util.List;
import java.util.Optional;
import app.wooportal.server.core.base.Query;
import app.wooportal.server.core.dto.listing.PageableList;
import app.wooportal.server.test.units.core.setup.services.RepoService;
import com.querydsl.core.types.Predicate;

public class TestChildRepositoryImpl implements TestChildRepository {
  
  private RepoService<TestChildEntity> repoService;
  
  public TestChildRepositoryImpl(List<TestChildEntity> data) {
    repoService = new RepoService<TestChildEntity>(data);
  }
  
  @Override
  public List<TestChildEntity> findAll() {
    return repoService.findAll();
  }
  
  @Override
  public PageableList<TestChildEntity> findAll(Query<TestChildEntity> query) {
    return repoService.findAll(query);
  }
    
  @Override
  public Optional<TestChildEntity> findOne(Predicate predicate) {
    return repoService.findOne(predicate);
  }

  @Override
  public <S extends TestChildEntity> List<S> saveAll(Iterable<S> entities) {
    return repoService.saveAll(entities);
  }
  
  @Override
  public void deleteById(String id) {
    repoService.deleteById(id);
  }

  @Override
  public <S extends TestChildEntity> S save(S entity) {
    return repoService.save(entity);
  }

  @Override
  public Optional<TestChildEntity> findById(String id) {
    return repoService.findById(id);
  }

  @Override
  public boolean existsById(String id) {
    return repoService.existsById(id);
  }

  @Override
  public Iterable<TestChildEntity> findAllById(Iterable<String> ids) {
    return repoService.findAllById(ids);
  }

  @Override
  public long count() {
    return repoService.count();
  }

  @Override
  public void delete(TestChildEntity entity) {
    repoService.delete(entity);
  }

  @Override
  public void deleteAll(Iterable<? extends TestChildEntity> entities) {
    repoService.deleteAll(entities);
  }
  
  @Override
  public void deleteAllById(Iterable<? extends String> ids) {
    repoService.deleteAllById(ids);
  }

  @Override
  public void deleteAll() {
    repoService.deleteAll();
  }

  @Override
  public boolean exists(Predicate predicate) {
    return repoService.exists(predicate);
  }

}
