package app.wooportal.server.components.location.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "addresses", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "house_number", "place", "postal_code", "street" })
})
public class AddressEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(name = "house_number")
  private String houseNumber;

  @Column(nullable = true)
  private Float latitude;

  @Column(nullable = true)
  private Float longitude;

  @Column(name = "place")
  private String place;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "street")
  private String street;


}
