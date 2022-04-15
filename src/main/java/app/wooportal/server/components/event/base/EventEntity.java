package app.wooportal.server.components.event.base;

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
import app.wooportal.server.components.event.category.EventCategoryEntity;
import app.wooportal.server.components.event.organizer.OrganizerEntity;
import app.wooportal.server.components.event.schedule.ScheduleEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.media.base.MediaEntity;
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
@Table(name = "events")
public class EventEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @Column(unique = true, nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private EventCategoryEntity category;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
  private List<ScheduleEntity> schedules;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private OrganizerEntity organizer;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "event_media",
      joinColumns = @JoinColumn(name = "event_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = { "event_id", "media_id" })
      })
  private List<MediaEntity> images;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private MediaEntity titleImage;
 
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private AddressEntity address;
  
}
