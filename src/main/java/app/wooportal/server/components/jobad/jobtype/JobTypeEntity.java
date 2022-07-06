package app.wooportal.server.components.jobad.jobtype;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.jobad.base.JobAdEntity;
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
@Table(name = "job_types")
public class JobTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @Column(nullable = false)
  private String color;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
  private Set<JobAdEntity> jobAds;

  @Column(nullable = false, unique = true)
  @DefaultSort
  private String name;

}
