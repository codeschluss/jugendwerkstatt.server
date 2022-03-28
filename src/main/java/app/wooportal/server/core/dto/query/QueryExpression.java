package app.wooportal.server.core.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QueryExpression {
  
  private QueryEntity entity;
  
  private QueryConjunction conjunction;

}
