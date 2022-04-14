package app.wooportal.server.components.documents.upload;

import org.springframework.stereotype.Service;
import app.wooportal.server.core.base.DataService;

@Service
public class UploadService extends DataService<UploadEntity, UploadPredicateBuilder> {

  public UploadService(UploadRepository repo, UploadPredicateBuilder predicate) {
    super(repo, predicate);

  }
}

