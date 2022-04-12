package app.wooportal.server.components.event.address;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.event.base.EventEntity;
import app.wooportal.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class AddressEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
  private List<EventEntity> event;

  @Column(nullable = true)
  private Float latitude;

  @Column(nullable = true)
  private Float longtitude;

  @Column(name = "house_number")
  private String houseNumber;

  @Column(name = "place")
  private String place;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "street")
  private String street;


}
