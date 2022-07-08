package app.wooportal.server.components.group.base;

import java.util.HashSet;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.components.messaging.chat.ChatEntity;
import app.wooportal.server.components.messaging.chat.ChatService;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class GroupService extends DataService<GroupEntity, GroupPredicateBuilder> {

  private final UserService userService;

  public GroupService(DataRepository<GroupEntity> repo, GroupPredicateBuilder predicate,
      ChatService chatService, UserService userService, CourseService courseService) {
    super(repo, predicate);

    addService("courses", courseService);
    addService("chat", chatService);

    this.userService = userService;

  }

  @Override
  public Optional<GroupEntity> getExisting(GroupEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<GroupEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }

  @Override
  protected void preSave(GroupEntity entity, GroupEntity newEntity, JsonNode context) {
    if (entity.getChat() == null) {
      var chat = new ChatEntity();
      chat.setName(newEntity.getName());
      chat.setAdmin(false);

      if (newEntity.getCourses() != null) {
        var participants = new HashSet<ParticipantEntity>();
        for (var user : userService
            .readAll(userService.query()
                .and(userService.getPredicate().withGroup(newEntity.getId())))
            .getList()) {
          
          var participant = new ParticipantEntity();
          participant.setChat(chat);
          participant.setUser(user);
          participants.add(participant);
        }
        chat.setParticipants(participants);
      }
      newEntity.setChat(chat);
      setContext("chat", context);
    }
  }
}
