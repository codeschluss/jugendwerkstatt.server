package app.wooportal.server.components.documents.upload;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class UploadService extends DataService<UploadEntity, UploadPredicateBuilder> {

  public UploadService(
      DataRepository<UploadEntity> repo,
      UploadPredicateBuilder predicate) {
    super(repo, predicate);

  }
}

