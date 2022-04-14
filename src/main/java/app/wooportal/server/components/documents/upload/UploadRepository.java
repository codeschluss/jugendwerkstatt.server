package app.wooportal.server.components.documents.upload;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface UploadRepository extends DataRepository<UploadEntity> {

}
