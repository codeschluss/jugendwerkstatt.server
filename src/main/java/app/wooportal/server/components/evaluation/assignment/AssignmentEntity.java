package app.wooportal.server.components.evaluation.assignment;

import java.io.Serial;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.evaluation.answer.AnswerEntity;
import app.wooportal.server.components.evaluation.assignmentState.AssignmentStateEntity;
import app.wooportal.server.components.evaluation.questionnaire.QuestionnaireEntity;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.security.components.user.UserEntity;
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
@Table(name = "assignments")
public class AssignmentEntity extends BaseEntity {

  @Serial
  private static final long serialVersionUID = 1L;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "assignment")
  private Set<AnswerEntity> answers;
  
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private AssignmentStateEntity assignmentState;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private QuestionnaireEntity questionnaire;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private UserEntity user;
}
