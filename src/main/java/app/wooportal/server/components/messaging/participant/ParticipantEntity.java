package app.wooportal.server.components.messaging.participant;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.messaging.chat.ChatEntity;
import app.wooportal.server.components.messaging.readReceipt.ReadReceiptEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "participants")
public class ParticipantEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @ManyToOne(fetch = FetchType.LAZY)
  private ChatEntity chat;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "participant")
  @EqualsAndHashCode.Exclude
  private Set<ReadReceiptEntity> readReceipts;
}
