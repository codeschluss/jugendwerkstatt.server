package app.wooportal.server.components.evaluation.question;

import org.springframework.stereotype.Service;
import app.wooportal.server.components.evaluation.answer.AnswerService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class QuestionService extends DataService<QuestionEntity, QuestionPredicateBuilder> {

  private final AnswerService answerService;
  public QuestionService(DataRepository<QuestionEntity> repo,
      QuestionPredicateBuilder predicate,
      AnswerService answerService) {
    super(repo, predicate);
    
    this.answerService = answerService;

  }
  public double calculateAverageRating(QuestionEntity question, Integer year){
    var answers = answerService.readAll(answerService.query()
        .and(answerService.getPredicate().withYear(year))
        .and(answerService.getPredicate().withQuestionId(question.getId()))).getList();
      
    var sum = 0.0;
    for (var answer : answers) {  
      if (answer.getRating() != null) {
        sum += answer.getRating().doubleValue();
      } else {
        answers.remove(answer);
      }
    }
    return answers != null && answers.size() > 0
        ? sum / answers.size()
        : sum;
  }
}

