package app.wooportal.server.test.units.core.setup.entities.image;

import java.util.List;
import java.util.Optional;
import app.wooportal.server.core.base.Query;
import app.wooportal.server.core.dto.listing.PageableList;
import app.wooportal.server.test.units.core.setup.services.RepoService;
import com.querydsl.core.types.Predicate;

public class TestImageRepositoryImpl implements TestImageRepository {
  
  private RepoService<TestImageEntity> repoService;
  
  public TestImageRepositoryImpl(List<TestImageEntity> data) {
    repoService = new RepoService<TestImageEntity>(data);
  }
  
  @Override
  public List<TestImageEntity> findAll() {
    return repoService.findAll();
  }
  
  @Override
  public PageableList<TestImageEntity> findAll(Query<TestImageEntity> query) {
    return repoService.findAll(query);
  }
  
  @Override
  public Optional<TestImageEntity> findOne(Predicate predicate) {
    return repoService.findOne(predicate);
  }

  @Override
  public <S extends TestImageEntity> List<S> saveAll(Iterable<S> entities) {
    return repoService.saveAll(entities);
  }
  
  @Override
  public void deleteById(String id) {
    repoService.deleteById(id);
  }

  @Override
  public <S extends TestImageEntity> S save(S entity) {
    return repoService.save(entity);
  }

  @Override
  public Optional<TestImageEntity> findById(String id) {
    return repoService.findById(id);
  }

  @Override
  public boolean existsById(String id) {
    return repoService.existsById(id);
  }

  @Override
  public Iterable<TestImageEntity> findAllById(Iterable<String> ids) {
    return repoService.findAllById(ids);
  }

  @Override
  public long count() {
    return repoService.count();
  }

  @Override
  public void delete(TestImageEntity entity) {
    repoService.delete(entity);
  }

  @Override
  public void deleteAll(Iterable<? extends TestImageEntity> entities) {
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
