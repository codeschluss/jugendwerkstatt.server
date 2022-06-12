package app.wooportal.server.components.settings;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SettingsService extends DataService<SettingsEntity, SettingsPredicateBuilder> {

  public SettingsService(
      DataRepository<SettingsEntity> repo, 
      SettingsPredicateBuilder predicate) {
    super(repo, predicate);
  }
  
}
