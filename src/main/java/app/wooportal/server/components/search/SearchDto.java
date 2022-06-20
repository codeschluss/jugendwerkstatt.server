package app.wooportal.server.components.search;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class SearchDto {

  private String title;

  private String content;

  private SearchResultType type;

  private String id;
}
