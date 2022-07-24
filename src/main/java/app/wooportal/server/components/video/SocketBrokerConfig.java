package app.wooportal.server.components.video;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

public class SocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/secured/user/queue/specific-user");
    config.setApplicationDestinationPrefixes("/spring-security-mvc-socket");
    config.setUserDestinationPrefix("/secured/user");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/secured/room").withSockJS();
  }
}
