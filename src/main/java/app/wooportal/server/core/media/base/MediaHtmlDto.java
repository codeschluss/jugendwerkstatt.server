package app.wooportal.server.core.media.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class MediaHtmlDto {

  private String name;
  
  private String html;
  
  public String getHtml() {
    if (!html.contains("<body>")) {
      html = String.format("<body>%s</body>", html);
    }
    
    if (!html.contains("<html>")) {
      html = String.format("<html>%s</html>", html);
    }
    
    return html;
  }
}
