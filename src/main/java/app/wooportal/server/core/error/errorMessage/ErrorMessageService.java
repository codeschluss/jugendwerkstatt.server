package app.wooportal.server.core.error.errorMessage;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ErrorMessageService extends DataService<ErrorMessageEntity, ErrorMessagePredicateBuilder>{

  public ErrorMessageService(
      DataRepository<ErrorMessageEntity> repo,
      ErrorMessagePredicateBuilder predicate) {
    super(repo, predicate);

  }

}
