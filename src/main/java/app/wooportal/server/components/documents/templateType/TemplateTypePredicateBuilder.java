package app.wooportal.server.components.documents.templateType;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class TemplateTypePredicateBuilder
    extends PredicateBuilder<QTemplateTypeEntity, TemplateTypeEntity> {

  public TemplateTypePredicateBuilder() {
    super(QTemplateTypeEntity.templateTypeEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String event) {
    return query.name.equalsIgnoreCase(event);
  }
}
