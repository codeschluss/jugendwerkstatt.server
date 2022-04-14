package app.wooportal.server.components.event.category;

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
@Table(name = "event_categories")
public class EventCategoryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String color;
  
  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private List<EventEntity> events;
  
  @Column(nullable = false)
  private String icon;

  @Column(unique = true, nullable = false, name = "name")
  private String name;

}
