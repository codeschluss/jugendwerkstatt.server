package app.wooportal.server.components.jobad.company;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.jobad.base.JobAdEntity;
import app.wooportal.server.components.location.address.AddressEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.DefaultSort;
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
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false) 
  private AddressEntity address;

  @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
  private Set<JobAdEntity> jobAd;
  
  @Column(unique = true)
  private String mail;

  @Column(unique = true, nullable = false)
  @DefaultSort
  private String name;

  private String phone;

  private String website;

}
