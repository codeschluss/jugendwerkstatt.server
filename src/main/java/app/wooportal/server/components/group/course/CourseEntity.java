package app.wooportal.server.components.group.course;

import java.io.Serial;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.group.base.GroupEntity;
import app.wooportal.server.components.group.feedback.FeedbackEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.DefaultSort;
import app.wooportal.server.core.security.components.user.UserEntity;
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
@Table(name = "courses")
public class CourseEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;
  
  private String description;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
  private List<FeedbackEntity> feedbacks;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private GroupEntity group;
  
  @Column(unique = true, nullable = false)
  @DefaultSort
  private String name;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
  private List<UserEntity> users;
}
