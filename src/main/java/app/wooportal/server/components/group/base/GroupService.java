package app.wooportal.server.components.group.base;

import java.util.HashSet;
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

  private final ParticipantService participantService;
  
  private final UserService userService;

  public GroupService(
      DataRepository<GroupEntity> repo,
      GroupPredicateBuilder predicate,
      ChatService chatService,
      ParticipantService participantService,
      UserService userService,
      CourseService courseService) {
    super(repo, predicate);
    
    addService("courses", courseService);
    addService("chat", chatService);
    
    this.participantService = participantService;
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

      if (newEntity.getUsers() != null) {
        var participants = new HashSet<ParticipantEntity>();
        for (var user : newEntity.getUsers()) {
          var participant = new ParticipantEntity();
          participant.setChat(chat);
          participant.setUser(user);
          participants.add(participant);
        }
      }
      newEntity.setChat(chat);
      setContext("chat", context);
    }
  }

  public boolean addMember(String groupId, String userId) {
    var group = getById(groupId);
    var user = userService.getById(userId);

    if (group.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("group or user does not exist", groupId);
    }

    participantService
        .deleteAll(participantService
            .readAll(participantService.query()
                .and(participantService.getPredicate().withUser(user.get().getId()).and(
                    participantService.getPredicate().witGroup(user.get().getGroup().getId()))))
            .getList());

    user.get().setGroup(group.get());
    userService.save(user.get());

    var participant = new ParticipantEntity();
    participant.setChat(group.get().getChat());
    participant.setUser(userService.getById(userId).get());
    participantService.save(participant);

    return true;
  }

  public boolean deleteMember(String groupId, String userId) {
    var group = getById(groupId);
    var user = userService.getById(userId);

    if (group.isEmpty() | user.isEmpty()) {
      throw new BadParamsException("group or user does not exist", groupId);
    }
    user.get().setGroup(null);
    userService.save(user.get());

    var participant = participantService
        .readAll(participantService.query().addGraph(participantService.graph("users", "chats"))
            .and(participantService.getPredicate().withUser(userId))
            .and(participantService.getPredicate().withChat(group.get().getChat().getId())))
        .getList();
    participantService.deleteAll(participant);

    return true;
  }

  public void updateActiveOrder() {
    var courseService = getService(CourseService.class);
    for (var group : repo.findAll(query().addGraph(graph("courses"))).getList()) {

      var courses = group.getCourses().stream()
          .sorted((o1, o2) -> o1.getActiveOrder().compareTo(o2.getActiveOrder()))
          .toList();

      for (int i = 0; i < courses.size(); i++) {
        if (courses.get(i).getActive() && i == courses.size() - 1) {
          courses.get(i).setActive(false);
          courses.get(0).setActive(true);
          courseService.save(courses.get(i));
          courseService.save(courses.get(0));
          break;
        } else if (courses.get(i).getActive()) {
          courses.get(i).setActive(false);
          courses.get(i + 1).setActive(true);
          courseService.save(courses.get(i));
          courseService.save(courses.get(i + 1));
          break;
        }
      }
    }
  }
}

