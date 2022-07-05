package app.wooportal.server.components.messaging.readReceipt;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class ReadReceiptApi extends CrudApi<ReadReceiptEntity, ReadReceiptService> {

  public ReadReceiptApi(ReadReceiptService ReadReceiptService) {
    super(ReadReceiptService);
  }

  @Override
  @GraphQLQuery(name = "getReadReceipts")
  @ApprovedAndVerifiedPermission
  public PageableList<ReadReceiptEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getReadReceipt")
  @ApprovedAndVerifiedPermission
  public Optional<ReadReceiptEntity> readOne(
      @GraphQLArgument(name = CrudApi.entity) ReadReceiptEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveReadReceipts")
  @ApprovedAndVerifiedPermission
  public List<ReadReceiptEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<ReadReceiptEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveReadReceipt")
  @ApprovedAndVerifiedPermission
  public ReadReceiptEntity saveOne(@GraphQLArgument(name = CrudApi.entity) ReadReceiptEntity entity) {
    var existing = service.getByUserAndMessage(entity.getMessage().getId());
    return existing.isPresent()
        ? existing.get()
        : super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteReadReceipts")
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteReadReceipt")
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }

}


