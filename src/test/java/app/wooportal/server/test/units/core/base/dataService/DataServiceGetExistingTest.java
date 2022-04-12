package app.wooportal.server.test.units.core.base.dataService;

import static app.wooportal.server.test.units.services.ObjectFactory.newTestEntity;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import app.wooportal.server.test.units.core.entities.base.TestEntity;
import app.wooportal.server.test.units.core.entities.base.TestPredicateBuilder;
import app.wooportal.server.test.units.core.entities.base.TestService;
import app.wooportal.server.test.units.services.RepoService;

public class DataServiceGetExistingTest {
  
  private TestService service;
  
  @BeforeEach
  public void init() {
    service = new TestService(
        new RepoService<TestEntity>(List.of(
            newTestEntity(Map.of("id", "1")),
            newTestEntity(Map.of("id", "2")))),
        new TestPredicateBuilder());
  }
  
  @Test
  public void getByIdOk() throws Exception {
    var test = "1";
    
    var result = service.getById(test);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get().getId()).isEqualTo(test);
  }
  
  @Test
  public void getByIdEmptyId() throws Exception {
    var test = "";
    
    var result = service.getById(test);
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void getByIdNullId() throws Exception {
    var result = service.getById(null);
    
    assertThat(result.isPresent()).isFalse();
  }

}
