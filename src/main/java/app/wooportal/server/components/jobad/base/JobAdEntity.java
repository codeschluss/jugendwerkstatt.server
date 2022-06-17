package app.wooportal.server.components.jobad.base;

import java.io.Serial;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.components.jobad.company.CompanyEntity;
import app.wooportal.server.components.jobad.jobtype.JobTypeEntity;
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
@Table(name = "job_ads")
public class JobAdEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private CompanyEntity company;
  
  @Column(nullable = false)
  private String content;
  
  @Column(name = "due_date")
  private OffsetDateTime dueDate;
  
  @Column(name = "start_date")
  private OffsetDateTime startDate;
  
  @Column(nullable = false)
  @DefaultSort
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private JobTypeEntity type;

}
