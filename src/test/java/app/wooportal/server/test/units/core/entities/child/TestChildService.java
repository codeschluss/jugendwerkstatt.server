package app.wooportal.server.test.units.core.entities.child;

import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

public class TestChildService  extends DataService<TestChildEntity, TestChildPredicateBuilder> {

  public TestChildService(
      DataRepository<TestChildEntity> repo, 
      TestChildPredicateBuilder entities) {
    super(repo, entities);
  }

}
