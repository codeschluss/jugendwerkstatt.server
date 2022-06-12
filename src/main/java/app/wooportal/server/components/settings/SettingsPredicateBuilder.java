package app.wooportal.server.components.settings;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class SettingsPredicateBuilder extends PredicateBuilder<QSettingsEntity, SettingsEntity> {

  public SettingsPredicateBuilder() {
    super(QSettingsEntity.settingsEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.id.likeIgnoreCase(term);
  }
  
}
