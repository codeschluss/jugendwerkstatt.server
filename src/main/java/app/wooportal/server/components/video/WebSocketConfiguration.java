package app.wooportal.server.components.video;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Service
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

  private final SocketHandler handler;

  public WebSocketConfiguration(SocketHandler handler) {
    this.handler = handler;
  }
  
  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(handler, "/videochat").setAllowedOrigins("*");
  }
}
