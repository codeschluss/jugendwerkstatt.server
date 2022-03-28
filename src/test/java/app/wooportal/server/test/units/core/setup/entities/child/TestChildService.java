package app.wooportal.server.test.units.core.setup.entities.child;

import app.wooportal.server.core.base.DataService;

public class TestChildService  extends DataService<TestChildEntity, TestChildPredicateBuilder> {

  public TestChildService(
      TestChildRepository repo, 
      TestChildPredicateBuilder entities) {
    super(repo, entities);
    // TODO Auto-generated constructor stub
  }

}
