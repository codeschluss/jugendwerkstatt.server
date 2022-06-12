package app.wooportal.server.components.settings;

import java.io.Serial;
import javax.persistence.Entity;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "settings")
public class SettingsEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;
  
  private Boolean chatActive;

}
