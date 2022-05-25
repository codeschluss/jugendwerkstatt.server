package app.wooportal.server.components.message.chat;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ChatRepository extends DataRepository<ChatEntity> {

}
