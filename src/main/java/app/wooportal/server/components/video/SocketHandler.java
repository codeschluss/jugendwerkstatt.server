package app.wooportal.server.components.video;


import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import app.wooportal.server.components.messaging.call.CallEntity;
import app.wooportal.server.components.messaging.call.CallService;
import app.wooportal.server.components.messaging.chat.ChatService;
import app.wooportal.server.components.messaging.participant.ParticipantService;
import app.wooportal.server.components.push.notification.NotificationEntity;
import app.wooportal.server.components.push.notification.NotificationService;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.security.components.user.UserEntity;
import app.wooportal.server.core.security.components.user.UserService;
import app.wooportal.server.core.security.services.AuthenticationService;
import app.wooportal.server.core.utils.ReflectionUtils;

@Service
public class SocketHandler extends TextWebSocketHandler {

  private final AuthenticationService authService;
  private final ChatService chatService;
  private final CallService callService;
  private final NotificationService notificationService;
  private final UserService userService;
  private final ParticipantService participantService;

  private ObjectMapper objectMapper;

  private ConcurrentHashMap<UserEntity, WebSocketSession> sessions = new ConcurrentHashMap<>();

  private ConcurrentHashMap<UserEntity, TextMessage> pendingOffers = new ConcurrentHashMap<>();

  public SocketHandler(AuthenticationService authService, ObjectMapper objectMapper,
      ChatService chatService, CallService callService, NotificationService notificationService,
      UserService userService, ParticipantService participantService) {
    this.authService = authService;
    this.objectMapper = objectMapper;
    this.chatService = chatService;
    this.callService = callService;
    this.notificationService = notificationService;
    this.userService = userService;
    this.participantService = participantService;

  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws IOException {}

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    var iterator = sessions.entrySet().iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getValue().equals(session)) {
        iterator.remove();
      }
    }
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
    var payload = objectMapper.readValue(message.getPayload(), CallPayloadDto.class);

    checkAuthorization(session, message, payload);
  }

  public void checkAuthorization(WebSocketSession session, TextMessage message,
      CallPayloadDto payload) throws IOException {

    var currentUser = authService.getUserFromToken(payload.getToken());
    if (!currentUser.isPresent() || currentUser.get().getApproved() == false) {
      sessions.remove(currentUser.get());
      session.close();
    }
    switch (payload.getType()) {
      case init -> {
        init(session, currentUser.get(), message, payload);
      }
      case offer -> {
        if (sessions.containsKey(currentUser.get())) {
          offer(session, currentUser.get(), payload.getChatId(), payload, message);
        }
      }
      case answer -> {
        if (sessions.containsKey(currentUser.get())) {
          answer(session, currentUser.get(), payload.getChatId(), payload, message);
        }
      }
      case abort -> {
        if (sessions.containsKey(currentUser.get())) {
          abort(session, currentUser.get(), payload.getChatId(), payload, message);
        }
      }
    }
  }

  public void init(WebSocketSession session, UserEntity currentUser, TextMessage message,
      CallPayloadDto payload) throws IOException {

    sessions.put(currentUser, session);
    if (pendingOffers.containsKey(currentUser)) {

      pendingOffers.remove(currentUser);
      sendMessage(sessions.get(currentUser), message, currentUser, payload);
    }
  }

  public void offer(WebSocketSession session, UserEntity currentUser, String chatId,
      CallPayloadDto payload, TextMessage message) throws IOException {

    var result =
        participantService
            .readAll(participantService.query()
                .and(participantService.getPredicate().withUser(currentUser.getId())
                    .and(participantService.getPredicate().withChat(chatId)))
                .setLimit(1))
            .getList();

    var chat = chatService.getById(chatId);

    if (result.isEmpty()) {
      throw new BadParamsException("No participant exists for user and chat", currentUser, chat);
    }

    var call = new CallEntity();
    call.setChat(chat.get());
    call.setInitiator(result.get(0));
    callService.save(call);

    var users = userService
        .readAll(userService.query().and(userService.getPredicate().withChat(chatId))).getList();
    users.remove(currentUser);

    for (var receivingUser : users) {
      if (sessions.containsKey(receivingUser)) {
        sendMessage(sessions.get(receivingUser), message, currentUser, payload);
      } else {
        pendingOffers.put(receivingUser, message);
        var notification = new NotificationEntity();
        notification.setTitle("Pending Call");
        notification.setContent(currentUser.getFullname());
        notification.setUser(receivingUser);
        notification.setRead(false);
        notificationService.save(notification);
      }
    }
  }

  public void answer(WebSocketSession session, UserEntity currentUser, String chatId,
      CallPayloadDto payload, TextMessage message) throws IOException {

    var users = userService
        .readAll(userService.query().and(userService.getPredicate().withChat(chatId))).getList();
    users.remove(currentUser);

    for (var receivingUser : users) {
      if (sessions.containsKey(receivingUser)) {

        sendMessage(sessions.get(receivingUser), message, currentUser, payload);
      } else {

        payload.setType(CallMessageType.abort);
        sendMessage(sessions.get(receivingUser), message, currentUser, payload);
      }
    }
  }

  public void abort(WebSocketSession session, UserEntity currentUser, String chatId,
      CallPayloadDto payload, TextMessage message) throws IOException {

    var users = userService
        .readAll(userService.query().and(userService.getPredicate().withChat(chatId))).getList();
    users.remove(currentUser);

    for (var receivingUser : users) {
      if (sessions.containsKey(receivingUser)) {

        sendMessage(sessions.get(receivingUser), message, currentUser, payload);
      }
    }
  }

  public void sendMessage(WebSocketSession session, TextMessage message, UserEntity user,
      CallPayloadDto payload) throws IOException {

    payload.setToken(null);
    ReflectionUtils.set("payload", message, objectMapper.writeValueAsString(payload));
    session.sendMessage(message);
  }
}

