package app.wooportal.server.test.units.core.entities.listChild;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.PredicateBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class TestListChildPredicateBuilder extends PredicateBuilder<QTestListChildEntity, TestListChildEntity>{

  public TestListChildPredicateBuilder() {
    super(QTestListChildEntity.testListChildEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }


  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
