package app.wooportal.server.components.video;


import java.io.IOException;
import java.util.Optional;
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
import app.wooportal.server.core.error.exception.InvalidTokenException;
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
    var currentUser = authService.getUserFromToken(payload.getToken());
    checkAuthorization(currentUser, session);
    checkValidMessage(payload, currentUser.get());
    
    switch (payload.getType()) {
      case init -> init(currentUser.get(), payload, session);
      case offer -> offer(currentUser.get(), payload, message);
      case answer -> answer(currentUser.get(), payload, message);
      case abort -> abort(currentUser.get(), payload, message);
    }
  }

  public void checkAuthorization(
      Optional<UserEntity> currentUser,
      WebSocketSession session) throws IOException {
    if (!currentUser.isPresent() || currentUser.get().getApproved() == false) {
      sessions.remove(currentUser.get());
      session.close();
      throw new InvalidTokenException("Token expired or user is not approved");
    }
  }
  
  private void checkValidMessage(CallPayloadDto payload, UserEntity currentUser) {
    if (payload.getType() != CallMessageType.init && !sessions.containsKey(currentUser)) {
      throw new BadParamsException("Send websocket with init type first!", payload);
    }
  }

  public void init(
      UserEntity currentUser,
      CallPayloadDto payload,
      WebSocketSession session) throws IOException {
    sessions.put(currentUser, session);
    if (pendingOffers.containsKey(currentUser)) {
      sendMessage(sessions.get(currentUser), pendingOffers.get(currentUser), payload);
      pendingOffers.remove(currentUser);
    }
  }

  public void offer(
      UserEntity currentUser,
      CallPayloadDto payload,
      TextMessage message) throws IOException {
    createCall(currentUser, payload);
    sendOffer(currentUser, payload, message);
  }
  
  private void createCall(
      UserEntity currentUser,
      CallPayloadDto payload) {
    var result =
        participantService
            .readAll(participantService.query()
                .and(participantService.getPredicate().withUser(currentUser.getId())
                    .and(participantService.getPredicate().withChat(payload.getChatId())))
                .setLimit(1))
            .getList();

    var chat = chatService.getById(payload.getChatId());

    if (result.isEmpty()) {
      throw new BadParamsException("No participant exists for user and chat", currentUser, chat);
    }

    var call = new CallEntity();
    call.setChat(chat.get());
    call.setInitiator(result.get(0));
    callService.save(call);
  }

  private void sendOffer(
      UserEntity currentUser,
      CallPayloadDto payload,
      TextMessage message) throws IOException {
    var users = userService
        .readAll(userService.query().and(userService.getPredicate().withChat(payload.getChatId()))).getList();
    users.remove(currentUser);

    for (var receivingUser : users) {
      if (sessions.containsKey(receivingUser)) {
        sendMessage(sessions.get(receivingUser), message, payload);
      } else {
        pendingOffers.put(receivingUser, message);
        var notification = new NotificationEntity();
        notification.setTitle("Eingehender Anruf");
        notification.setContent(currentUser.getFullname());
        notification.setUser(receivingUser);
        notification.setRead(false);
        notificationService.save(notification);
      }
    }
  }

  public void answer(
      UserEntity currentUser,
      CallPayloadDto payload,
      TextMessage message) throws IOException {
    /**
     * TODO: The following logic does not work properly if a chat contains more than two
     * participants. The problem is that we send answers to all chat users, including those which
     * never send an offer. For chats with only two users this will work implicitly but has to be
     * reworked once group calls are necessary (e.g. have an in-memory map for already connected
     * users of a chat, etc.)
     */
    
    var users = userService
        .readAll(userService.query().and(userService.getPredicate().withChat(payload.getChatId()))).getList();
    users.remove(currentUser); 

    for (var receivingUser : users) {
      if (sessions.containsKey(receivingUser)) {
        sendMessage(sessions.get(receivingUser), message, payload);
      } else {
        payload.setType(CallMessageType.abort);
        sendMessage(sessions.get(receivingUser), message, payload);
      }
    }
  }

  public void abort(
      UserEntity currentUser,
      CallPayloadDto payload,
      TextMessage message) throws IOException {

    var users = userService
        .readAll(userService.query().and(userService.getPredicate().withChat(payload.getChatId()))).getList();
    users.remove(currentUser);

    for (var receivingUser : users) {
      if (sessions.containsKey(receivingUser)) {
        sendMessage(sessions.get(receivingUser), message, payload);
      }
    }
  }

  public void sendMessage(
      WebSocketSession session,
      TextMessage message,
      CallPayloadDto payload) throws IOException {
    payload.setToken(null);
    ReflectionUtils.set("payload", message, objectMapper.writeValueAsString(payload));
    session.sendMessage(message);
  }
}

