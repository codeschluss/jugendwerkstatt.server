package app.wooportal.server.components.documents.upload;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class UploadPredicateBuilder extends PredicateBuilder<QUploadEntity, UploadEntity> {

  public UploadPredicateBuilder() {
    super(QUploadEntity.uploadEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }

  public BooleanExpression withName(String event) {
    return query.user.fullname.equalsIgnoreCase(event);
  }
}
