package app.wooportal.server.test.units.core.setup.entities.image;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.config.DefaultSort;
import app.wooportal.server.core.image.ImageEntity;
import app.wooportal.server.test.units.core.setup.entities.base.TestEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Table(name = "test_image")
@Entity
public class TestImageEntity extends ImageEntity {
  
  private static final long serialVersionUID = 1L;
  
  @DefaultSort(field = "name2")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private TestEntity parent;
  
}
