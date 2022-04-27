package app.wooportal.server.components.page.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class PagePredicateBuilder extends PredicateBuilder<QPageEntity, PageEntity> {

  public PagePredicateBuilder() {
    super(QPageEntity.pageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.slug.likeIgnoreCase(term).or(query.content.likeIgnoreCase(term));
  }

  public BooleanExpression withName(String name) {
    return query.slug.equalsIgnoreCase(name);
  }
}
