package app.wooportal.server.components.messaging.participant;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ParticipantRepository extends DataRepository<ParticipantEntity> {

}
