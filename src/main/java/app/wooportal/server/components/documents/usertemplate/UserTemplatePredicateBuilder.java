package app.wooportal.server.components.documents.usertemplate;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class UserTemplatePredicateBuilder
    extends PredicateBuilder<QUserTemplateEntity, UserTemplateEntity> {

  public UserTemplatePredicateBuilder() {
    super(QUserTemplateEntity.userTemplateEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term).or(query.content.likeIgnoreCase(term))
        .or(query.templateType.name.likeIgnoreCase(term));
  }

  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
}
