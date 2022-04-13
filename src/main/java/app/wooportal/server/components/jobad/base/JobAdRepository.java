package app.wooportal.server.components.jobad.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface JobAdRepository extends DataRepository<JobAdEntity> {

}
