package app.wooportal.server.components.evaluation.questionnaire;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.evaluation.assignment.AssignmentEntity;
import app.wooportal.server.components.evaluation.question.QuestionEntity;
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
@Table(name = "questionnaires")
public class QuestionnaireEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire")
  private Set<AssignmentEntity> assignments;

  @Column(unique = true, nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "questionnaire")
  private Set<QuestionEntity> questions;
}
