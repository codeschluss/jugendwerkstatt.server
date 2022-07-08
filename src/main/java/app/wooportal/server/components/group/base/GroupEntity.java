package app.wooportal.server.components.group.base;

import java.io.Serial;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import app.wooportal.server.components.group.course.CourseEntity;
import app.wooportal.server.components.messaging.chat.ChatEntity;
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
@Table(name = "groups")
public class GroupEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
  private List<CourseEntity> courses;
  
  private String description;
  
  @Column(unique = true, nullable = false)
  @DefaultSort
  private String name;
  
  @OneToOne(fetch = FetchType.LAZY)
  private ChatEntity chat;
  
}
