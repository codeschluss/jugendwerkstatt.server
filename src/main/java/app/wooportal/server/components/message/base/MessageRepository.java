package app.wooportal.server.components.message.base;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MessageRepository extends DataRepository<MessageEntity> {

}
