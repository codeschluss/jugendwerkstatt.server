package app.wooportal.server.components.messaging.readReceipt;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.components.messaging.message.MessageEntity;
import app.wooportal.server.components.messaging.participant.ParticipantEntity;
import app.wooportal.server.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "read_receipts")
public class ReadReceiptEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private MessageEntity message;

  @ManyToOne(fetch = FetchType.LAZY)
  private ParticipantEntity participant;
}
