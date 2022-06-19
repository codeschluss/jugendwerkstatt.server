package app.wooportal.server.components.messaging.chat;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import app.wooportal.server.components.group.base.GroupEntity;
import app.wooportal.server.components.messaging.message.MessageEntity;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "chats")
public class ChatEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private Boolean admin;

  @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  private Set<MessageEntity> messages;

  @Column(unique = true, nullable = false)
  private String name;
  
  @OneToOne(mappedBy = "chat", fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  private GroupEntity group;
  
  @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  private Set<MessageEntity> participants;
  
}

