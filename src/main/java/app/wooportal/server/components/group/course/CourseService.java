package app.wooportal.server.components.group.course;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.group.feedback.FeedbackService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class CourseService extends DataService<CourseEntity, CoursePredicateBuilder> {

  
  private final FeedbackService feedbackService;
  public CourseService(DataRepository<CourseEntity> repo,
      CoursePredicateBuilder predicate,
      FeedbackService feedbackService) {
    super(repo, predicate);
   
   this.feedbackService = feedbackService;
  }

  @Override
  public Optional<CourseEntity> getExisting(CourseEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<CourseEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
  
  public double calculateAverageRating(CourseEntity course, Integer year){
   
    var feedbacks = feedbackService.readAll(feedbackService.query()
        .and(feedbackService.getPredicate().withYear(year))
        .and(feedbackService.getPredicate().withCourseId(course.getId()))).getList();
      
    var sum = 0.0;
    for (var feedback : feedbacks) {
      if(feedback != null) {
      sum += feedback.getRating().doubleValue();
      }
      else {
        feedbacks.remove(feedback);
      }
    }
    return feedbacks != null && feedbacks.size() > 0
        ? sum / feedbacks.size()
        : sum;
  }
}
