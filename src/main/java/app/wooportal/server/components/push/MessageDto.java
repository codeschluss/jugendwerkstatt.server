package app.wooportal.server.components.push;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class MessageDto {

  private String title;
  
  private String content;
  
  private NotificationType type;
  
  private Map<String, String> data = new HashMap<>();
  
  public MessageDto(
      String title,
      String content,
      Map<String, String> data,
      NotificationType type) {
    setTitle(title);
    setContent(content);
    setData(data);
    setType(type);
  }
  
}
