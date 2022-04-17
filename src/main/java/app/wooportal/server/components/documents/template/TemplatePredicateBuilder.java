package app.wooportal.server.components.documents.template;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class TemplatePredicateBuilder extends PredicateBuilder<QTemplateEntity, TemplateEntity> {

  public TemplatePredicateBuilder() {
    super(QTemplateEntity.templateEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term).or(query.content.likeIgnoreCase(term))
        .or(query.templateType.name.likeIgnoreCase(term));

  }

  public BooleanExpression withTitle(String name) {
    return query.name.equalsIgnoreCase(name);
  }

}
