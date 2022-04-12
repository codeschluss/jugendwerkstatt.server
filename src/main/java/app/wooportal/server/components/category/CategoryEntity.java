package app.wooportal.server.components.category;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import app.wooportal.server.components.event.EventEntity;
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
@Table(name = "category")
public class CategoryEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<EventEntity> events;

  @Column(unique = true, nullable = false, name = "name")
  private String name;
  
  @Column(nullable = false)
  private String color;

  @Column(nullable = false)
  private String icon;
}
