package app.wooportal.server.components.evaluation.question;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import app.wooportal.server.components.evaluation.answer.AnswerEntity;
import app.wooportal.server.components.evaluation.questionnaire.QuestionnaireEntity;
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
@Table(name = "questions")
public class QuestionEntity extends BaseEntity {

  private static final long serialVersionUID = 1L;
  
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
  private List<AnswerEntity> answers;

  @Column(nullable = false)
  private String item;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private QuestionnaireEntity questionnaire;

}