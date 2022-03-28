package app.wooportal.server.test.units.core.setup.entities.image;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.PredicateBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Service
public class TestImagePredicateBuilder extends PredicateBuilder<QTestImageEntity, TestImageEntity>{

  public TestImagePredicateBuilder() {
    super(QTestImageEntity.testImageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.parent.name.likeIgnoreCase(term);
  }

}
