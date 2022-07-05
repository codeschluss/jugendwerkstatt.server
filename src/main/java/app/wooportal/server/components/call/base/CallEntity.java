package app.wooportal.server.components.call.base;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.components.messaging.chat.ChatEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
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
@Table(name = "calls")
public class CallEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity initiator;

  @ManyToOne(fetch = FetchType.LAZY)
  private ChatEntity chat;
}

