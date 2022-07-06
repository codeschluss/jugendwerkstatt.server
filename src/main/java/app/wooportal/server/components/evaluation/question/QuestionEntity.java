package app.wooportal.server.components.evaluation.question;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import app.wooportal.server.components.evaluation.answer.AnswerEntity;
import app.wooportal.server.components.evaluation.questionnaire.QuestionnaireEntity;
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
@Table(name = "questions",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"item", "questionnaire_id"})})
public class QuestionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
  private Set<AnswerEntity> answers;

  @Column(nullable = false)
  private String item;

  @Column(nullable = false)
  @DefaultSort
  private Integer sequenceOrder;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private QuestionnaireEntity questionnaire;

}
