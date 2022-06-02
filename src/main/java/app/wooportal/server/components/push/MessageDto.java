package app.wooportal.server.components.push;

import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
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
      Map<String, String> data) {
    setTitle(title);
    setContent(content);
    setData(data);
  }
  
}
