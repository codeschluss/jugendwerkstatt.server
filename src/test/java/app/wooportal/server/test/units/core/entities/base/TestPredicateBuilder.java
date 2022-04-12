package app.wooportal.server.test.units.core.entities.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.PredicateBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class TestPredicateBuilder extends PredicateBuilder<QTestEntity, TestEntity>{

  public TestPredicateBuilder() {
    super(QTestEntity.testEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

}
