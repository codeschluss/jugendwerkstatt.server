package app.wooportal.server.components.jobad.jobtype;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.jobad.base.JobAdEntity;
import app.wooportal.server.core.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "job_types")
public class JobTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
  private List<JobAdEntity> jobAds;

  @Column(nullable = false, unique = true)
  private String name;

}
