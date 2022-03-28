package app.wooportal.server.core.security.components.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.security.components.role.RoleEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Entity
@Table(name = "users")
@GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator"
)
public class UserEntity extends BaseEntity {
  
  private static final long serialVersionUID = 1L;
  
  private String fullname;
  
  @Column(
      unique = true, 
      nullable = false,
      name = "login_name")
  private String loginName;
 
  @Column(nullable = false)
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;
  
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = { "user_id", "role_id" })
      })
  @CollectionId(
      column = @Column(name = "id"),
      type = @Type(type = "uuid-char"),
      generator = "UUID"
  )
  private List<RoleEntity> roles = new ArrayList<>();
  
}
