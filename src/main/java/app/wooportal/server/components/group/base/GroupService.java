package app.wooportal.server.components.group.base;

import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.group.course.CourseService;
import app.wooportal.server.components.messaging.chat.ChatEntity;
import app.wooportal.server.components.messaging.chat.ChatService;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.security.components.user.UserService;

@Service
public class GroupService extends DataService<GroupEntity, GroupPredicateBuilder> {

  private final ChatService chatService;
  private final ParticipantService participantService;
  private final UserService userService;

  public GroupService(DataRepository<GroupEntity> repo,
      GroupPredicateBuilder predicate,
      ChatService chatService,
      CourseService courseService,
      ParticipantService participantService,
      UserService userService) {
    super(repo, predicate);
    this.chatService = chatService;
    this.participantService = participantService;
    this.userService = userService;

    addService("courses", courseService);
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
  protected void postSave(GroupEntity saved, GroupEntity newEntity, JsonNode context) {

    if (saved.getChat() == null) {

      var chat = new ChatEntity();
      chat.setName(saved.getName());
      chat.setAdmin(false);
      chatService.save(chat);
      newEntity.setChat(chat);
      repo.save(newEntity);

      if (saved.getUsers() != null) {
        for (var user : saved.getUsers()) {

          var participant = new ParticipantEntity();
          participant.setChat(chat);
          participant.setUser(user);
          participantService.save(participant);
        }
      }
    }
  }

  public GroupEntity addMember(String groupId, String userId) {

    var group = getById(groupId);
    var user = userService.getById(userId);

    if (group.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("group or user does not exist", groupId);
    }
    user.get().setGroup(group.get());
    userService.save(user.get());



    var participant = new ParticipantEntity();
    participant.setChat(group.get().getChat());
    participant.setUser(userService.getById(userId).get());

    if (participantService.getByExample(participant).isEmpty()) {
      participantService.save(participant);
    }
    group.get().getUsers().add(user.get());
    return repo.save(group.get());
  }

  public GroupEntity deleteMember(String groupId, String userId) {

    var group = getById(groupId);
    var user = userService.getById(userId);

    if (group.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("group or user does not exist", groupId);
    }
    user.get().setGroup(null);
    userService.save(user.get());

    var participant = participantService
        .readAll(participantService.query()
            .addGraph(participantService.graph("users", "chats"))
            .and(participantService.getPredicate().withUser(userId))
            .and(participantService.getPredicate().withChat(group.get().getChat().getId())))
        .getList();
    participantService.deleteAll(participant);

    group.get().getUsers().add(user.get());
    return repo.save(group.get());
  }
}
