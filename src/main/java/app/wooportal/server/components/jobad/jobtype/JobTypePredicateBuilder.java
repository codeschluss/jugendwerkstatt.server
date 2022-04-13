package app.wooportal.server.components.jobad.jobtype;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class JobTypePredicateBuilder extends PredicateBuilder<QJobTypeEntity, JobTypeEntity> {

  public JobTypePredicateBuilder() {
    super(QJobTypeEntity.jobTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String event) {
    return query.name.equalsIgnoreCase(event);
  }
}
