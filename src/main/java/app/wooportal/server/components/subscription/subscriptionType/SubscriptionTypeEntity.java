package app.wooportal.server.components.subscription.subscriptionType;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.subscription.base.SubscriptionEntity;
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
@Table(name = "subscription_types")
public class SubscriptionTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String description;

  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany(mappedBy = "subscriptionType", fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  private Set<SubscriptionEntity> subscriptions;
}
