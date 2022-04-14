package app.wooportal.server.components.link.base;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class LinkPredicateBuilder extends PredicateBuilder<QLinkEntity, LinkEntity> {

  public LinkPredicateBuilder() {
    super(QLinkEntity.linkEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.title.likeIgnoreCase(term).or(query.url.likeIgnoreCase(term))
        .or(query.category.name.likeIgnoreCase(term));
  }

  public BooleanExpression withTitle(String name) {
    return query.title.equalsIgnoreCase(name);
  }
}
