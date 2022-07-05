package app.wooportal.server.components.call.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CallRepository extends DataRepository<CallEntity> {

}
