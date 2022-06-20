package app.wooportal.server.components.search;

import java.util.List;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class SearchApi {

  private final SearchService searchService;

  public SearchApi(SearchService searchService) {

    this.searchService = searchService;
  }

  @GraphQLQuery(name = "search")
  @ApprovedAndVerifiedPermission
  public List<SearchDto> search(FilterSortPaginate params) {
    return searchService.search(params);
  }
}
