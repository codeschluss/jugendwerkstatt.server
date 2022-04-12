package app.wooportal.server.components.event;

import java.io.Serial;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.components.address.AddressEntity;
import app.wooportal.server.components.category.CategoryEntity;
import app.wooportal.server.components.organizer.OrganizerEntity;
import app.wooportal.server.components.schedule.ScheduleEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.image.ImageEntity;
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
@Table(name = "event")
public class EventEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @Column(unique = true, nullable = false, name = "name")
  private String name;

  @Column(nullable = false, name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private CategoryEntity category;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
  private List<ScheduleEntity> schedules;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private OrganizerEntity organizer;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "event_images",
      joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "image_id"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = { "event_id", "image_id" })
      })
  private List<ImageEntity> image;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ImageEntity title_image;
 
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private AddressEntity address;
  
}
