package app.wooportal.server.components.link.category;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class LinkCategoryPredicateBuilder extends PredicateBuilder<QLinkCategoryEntity, LinkCategoryEntity> {

  public LinkCategoryPredicateBuilder() {
    super(QLinkCategoryEntity.linkCategoryEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
