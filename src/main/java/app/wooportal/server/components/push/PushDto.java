package app.wooportal.server.components.push;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class PushDto {

  private String title;
  
  private String content;
  
  private String route;
  
  public PushDto(
      String title,
      String content) {
    setTitle(title);
    setContent(content);
  }
}
