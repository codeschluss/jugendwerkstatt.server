package app.wooportal.server.components.video;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CallPayloadDto {
  
  private String token;
  
  private String chatId;
  
  private CallMessageType type;
  
  private String sdp;

}
