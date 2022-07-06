package app.wooportal.server.components.messaging.participant;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.components.messaging.chat.ChatEntity;
import app.wooportal.server.components.messaging.message.MessageEntity;
import app.wooportal.server.components.messaging.readReceipt.ReadReceiptEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participants", uniqueConstraints =
  @UniqueConstraint(columnNames = { "chat_id", "user_id" }))
public class ParticipantEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private ChatEntity chat;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "participant")
  private Set<MessageEntity> messages;
  
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "participant")
  private Set<ReadReceiptEntity> readReceipts;
}
