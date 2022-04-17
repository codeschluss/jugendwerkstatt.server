package app.wooportal.server.core.error.errorMessage;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.error.ErrorService;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Component
@GraphQLApi
public class ErrorMessageApi extends CrudApi<ErrorMessageEntity, ErrorMessageService> {

  private final ErrorService errorMailService;
  
  public ErrorMessageApi(
      ErrorMessageService service,
      ErrorService errorMailService) {
    super(service);
    this.errorMailService = errorMailService;
  }
  
  @Override
  @GraphQLQuery(name = "getErrorMessages")
  public PageableList<ErrorMessageEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getErrorMessage")
  public Optional<ErrorMessageEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ErrorMessageEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveErrorMessages")
  public List<ErrorMessageEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ErrorMessageEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveErrorMessage")
  public ErrorMessageEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ErrorMessageEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteErrorMessages")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteErrorMessage")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }
  
  @GraphQLMutation(name = "sendError")
  public void sendError(String stackTrace) {
    errorMailService.sendErrorMail(stackTrace);
  }
  
}