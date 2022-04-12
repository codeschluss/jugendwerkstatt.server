package app.wooportal.server.test.units.core.base.predicatebuilder;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import app.wooportal.server.test.units.core.entities.base.TestPredicateBuilder;
import app.wooportal.server.test.units.services.QueryDslAssertion;
import com.querydsl.core.types.Ops;

public class PredicateBuilderWithIdTest {
  
  private TestPredicateBuilder testBuilder = new TestPredicateBuilder();
  
  @Test
  public void withIdOk() throws Exception {       
    var test = "test";
    
    var result = testBuilder.withId(test);
    
    assertThat(result.isPresent()).isTrue();
    QueryDslAssertion.assertOperator(Ops.EQ, result.get());
    QueryDslAssertion.assertPath("id", result.get());
    QueryDslAssertion.assertValue("test", result.get());
  }

}
