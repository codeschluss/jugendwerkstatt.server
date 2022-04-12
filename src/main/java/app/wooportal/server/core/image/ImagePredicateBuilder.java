package app.wooportal.server.core.image;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class ImagePredicateBuilder extends PredicateBuilder<QImageEntity, ImageEntity> {

  public ImagePredicateBuilder() {
    super(QImageEntity.imageEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }

}
