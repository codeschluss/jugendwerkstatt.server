package app.wooportal.server.components.address;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AddressPredicateBuilder extends PredicateBuilder<QAddressEntity, AddressEntity> {

  public AddressPredicateBuilder() {
    super(QAddressEntity.addressEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.street.likeIgnoreCase(term)
        .or(query.place.likeIgnoreCase(term))
        .or(query.houseNumber.likeIgnoreCase(term))
        .or(query.postalCode.likeIgnoreCase(term));
  }
}