package app.wooportal.server.components.page.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PageRepository extends DataRepository<PageEntity> {

}
