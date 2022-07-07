package app.wooportal.server.components.messaging.call;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.components.messaging.chat.ChatEntity;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
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
@Table(name = "calls")
public class CallEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private ParticipantEntity initiator;

  @ManyToOne(fetch = FetchType.LAZY)
  private ChatEntity chat;
}

