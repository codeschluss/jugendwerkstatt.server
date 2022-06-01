package app.wooportal.server.components.messaging.readReceipt;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class ReadReceiptService extends DataService<ReadReceiptEntity, ReadReceiptPredicateBuilder> {

  public ReadReceiptService(DataRepository<ReadReceiptEntity> repo, ReadReceiptPredicateBuilder predicate) {
    super(repo, predicate);

  }
}

