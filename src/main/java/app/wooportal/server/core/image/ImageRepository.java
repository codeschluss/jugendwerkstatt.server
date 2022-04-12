package app.wooportal.server.core.image;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ImageRepository extends DataRepository<ImageEntity> {

}
