package app.wooportal.server.components.link.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LinkRepository extends DataRepository<LinkEntity> {

}
