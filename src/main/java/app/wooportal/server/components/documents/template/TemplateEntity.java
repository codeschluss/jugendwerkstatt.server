package app.wooportal.server.components.documents.template;

import java.io.Serial;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import app.wooportal.server.components.documents.templateType.TemplateTypeEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.config.DefaultSort;
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
@Table(name = "templates")
public class TemplateEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false, unique = true)
  @DefaultSort
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private TemplateTypeEntity templateType;

}
