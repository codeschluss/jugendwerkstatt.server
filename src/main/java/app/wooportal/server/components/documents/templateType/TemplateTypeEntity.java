package app.wooportal.server.components.documents.templateType;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.documents.template.TemplateEntity;
import app.wooportal.server.components.documents.usertemplate.UserTemplateEntity;
import app.wooportal.server.core.base.BaseEntity;
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
@Table(name = "template_types")
public class TemplateTypeEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @Column(nullable = false, unique = true)
  private String name;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "templateType")
  private List<TemplateEntity> templates;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "templateType")
  private List<UserTemplateEntity> userTemplates;

}
