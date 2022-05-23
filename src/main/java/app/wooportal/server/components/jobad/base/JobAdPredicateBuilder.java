package app.wooportal.server.components.jobad.base;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class JobAdPredicateBuilder extends PredicateBuilder<QJobAdEntity, JobAdEntity> {

  public JobAdPredicateBuilder() {
    super(QJobAdEntity.jobAdEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.title.likeIgnoreCase(term)
        .or(query.company.name.likeIgnoreCase(term))
        .or(query.type.name.likeIgnoreCase(term));
  }

  public BooleanExpression withTitle(String title) {
    return query.title.equalsIgnoreCase(title);
  }
  public BooleanExpression withDate(OffsetDateTime date) {
    return query.dueDate.after(date.withMinute(0).withHour(0))
      .and(query.dueDate.before(date.withMinute(59).withHour(23)));
}
}