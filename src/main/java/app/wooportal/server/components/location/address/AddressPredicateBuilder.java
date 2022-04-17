package app.wooportal.server.components.location.address;
import org.springframework.stereotype.Service;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class AddressPredicateBuilder extends PredicateBuilder<QAddressEntity, AddressEntity> {

  public AddressPredicateBuilder() {
    super(QAddressEntity.addressEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.houseNumber.likeIgnoreCase(term)
        .or(query.place.likeIgnoreCase(term))
        .or(query.postalCode.likeIgnoreCase(term))
        .or(query.street.likeIgnoreCase(term));
  }

  public Predicate withFullAddress(AddressEntity entity) {
    var builder = new BooleanBuilder();
    return builder
        .and(withStreet(entity.getStreet()))
        .and(withHouseNumber(entity.getHouseNumber()))
        .and(withPlace(entity.getPlace()))
        .and(withPostalCode(entity.getPostalCode()))
        .getValue();
  }

  public BooleanExpression withPostalCode(String postalCode) {
    return postalCode != null
        ? query.postalCode.eq(postalCode)
        : null;
  }

  public BooleanExpression withPlace(String place) {
    return place != null
        ? query.place.eq(place)
        : null;
  }

  public BooleanExpression withHouseNumber(String houseNumber) {
    return houseNumber != null
        ? query.houseNumber.eq(houseNumber)
        : null;
  }

  public BooleanExpression withStreet(String street) {
    return street != null
        ? query.street.eq(street)
        : null;
  }
}
