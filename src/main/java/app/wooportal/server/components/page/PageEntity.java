package app.wooportal.server.components.page;

import java.io.Serial;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.DefaultSort;
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
@Table(name = "pages")
public class PageEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  private String content;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "page_image_media", joinColumns = @JoinColumn(name = "page_id"),
      inverseJoinColumns = @JoinColumn(name = "media_id"),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"page_id", "media_id"})})
  private List<MediaEntity> images;
  
  @Column(nullable = false, unique = true)
  @DefaultSort
  private String name;

  @Column(nullable = false, unique = true)
  private String slug;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity titleImage;

  @ManyToOne(fetch = FetchType.LAZY)
  private MediaEntity video;
}
