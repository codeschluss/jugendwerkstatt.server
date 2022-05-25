package app.wooportal.server.components.push.notification;

import java.io.Serial;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "notifications")
public class NotificationEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private Boolean read;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserEntity user;

}
