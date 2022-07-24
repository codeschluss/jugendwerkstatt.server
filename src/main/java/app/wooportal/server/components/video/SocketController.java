package app.wooportal.server.components.video;

import java.security.Principal;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import app.wooportal.server.components.push.MessageDto;

@Controller
public class SocketController {
  
  private SimpMessagingTemplate simpMessagingTemplate;

  @MessageMapping("/secured/room") 
  public void sendSpecific(
    @Payload MessageDto msg, 
    Principal user, 
    @Header("simpSessionId") String sessionId) throws Exception { 
      MessageDto out = new MessageDto(); 
      out.setContent("hello");
      simpMessagingTemplate.convertAndSendToUser(
        msg.getTitle(), "/secured/user/queue/specific-user", out.getContent()); 
  }
}
