package app.wooportal.server.test.units.core.entities.image;

import app.wooportal.server.core.base.DataService;

public class TestImageService  extends DataService<TestImageEntity, TestImagePredicateBuilder> {

  public TestImageService(
      TestImageRepository repo, 
      TestImagePredicateBuilder entities) {
    super(repo, entities);
    // TODO Auto-generated constructor stub
  }

}
