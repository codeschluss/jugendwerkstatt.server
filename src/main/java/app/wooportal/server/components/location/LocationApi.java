package app.wooportal.server.components.location;

import javax.naming.ServiceUnavailableException;
import org.springframework.stereotype.Component;
import app.wooportal.server.components.location.bingMaps.BingMapService;
import app.wooportal.server.components.location.bingMaps.model.route.RouteResource;
import app.wooportal.server.components.location.dto.LocationParam;
import app.wooportal.server.core.error.exception.BadParamsException;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class LocationApi {
  
  private final BingMapService mapService;
  
  public LocationApi(
      BingMapService mapService) {
    this.mapService = mapService;
  }
  
  @GraphQLQuery(name = "calculateRoute")
  @ApprovedAndVerifiedPermission
  public RouteResource calculateRoute(
      LocationParam params) throws ServiceUnavailableException {
    if (isValid(params)) {
      return mapService.calculateRoute(params); 
    }
    throw new BadParamsException("Start or target is empty");
  }

  private boolean isValid(LocationParam params) {
    return params.getStartPoint() != null
        && (params.getStartPoint().getLatitude() != 0 
          || params.getStartPoint().getLongitude() != 0)
      && params.getTargetPoint() != null
        && (params.getTargetPoint().getLatitude() != 0
          || params.getTargetPoint().getLongitude() != 0);
  }

}
