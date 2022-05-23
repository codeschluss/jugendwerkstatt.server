package app.wooportal.server.components.subscription.base;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class SubscriptionService
    extends DataService<SubscriptionEntity, SubscriptionPredicateBuilder> {

  public SubscriptionService(DataRepository<SubscriptionEntity> repo,
      SubscriptionPredicateBuilder predicate) {
    super(repo, predicate);
  }

  @Override
  public Optional<SubscriptionEntity> getExisting(SubscriptionEntity entity) {
    return entity.getAuth_secret() == null || entity.getAuth_secret().isEmpty() ? Optional.empty()
        : getByAuth_Secret(entity.getAuth_secret());
  }

  public Optional<SubscriptionEntity> getByAuth_Secret(String key) {
    return repo.findOne(predicate.withAuth_Secret(key));
  }

  public Optional<SubscriptionEntity> getBySubscriptionType(String name) {
    return repo.findOne(predicate.withSubscriptionType(name));
  }

  public List<SubscriptionEntity> getAllSubscriptions(String... graph) {
    return repo.findAll(query(false).addGraph(graph(graph))).getList();
  }
}
