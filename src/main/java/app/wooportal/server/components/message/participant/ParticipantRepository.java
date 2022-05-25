package app.wooportal.server.components.message.participant;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ParticipantRepository extends DataRepository<ParticipantEntity> {

}
