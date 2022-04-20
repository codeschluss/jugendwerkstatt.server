package app.wooportal.server.components.group.feedback;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class FeedbackService extends DataService<FeedbackEntity, FeedbackPredicateBuilder> {

  public FeedbackService(DataRepository<FeedbackEntity> repo, FeedbackPredicateBuilder predicate) {
    super(repo, predicate);

  }
}
