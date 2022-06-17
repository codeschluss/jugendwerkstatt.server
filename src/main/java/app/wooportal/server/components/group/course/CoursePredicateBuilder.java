package app.wooportal.server.components.group.course;

import org.springframework.stereotype.Service;
import com.querydsl.core.types.dsl.BooleanExpression;
import app.wooportal.server.components.group.base.GroupEntity;
import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class CoursePredicateBuilder extends PredicateBuilder<QCourseEntity, CourseEntity> {

  public CoursePredicateBuilder() {
    super(QCourseEntity.courseEntity);
  }

  @Override
  public BooleanExpression freeSearch(String term) {
    return query.name.likeIgnoreCase(term);     
  }
  
  public BooleanExpression withName(String name) {
    return query.name.equalsIgnoreCase(name);
  }
  
  public BooleanExpression isActive() {
    return query.active.isTrue();
  }
  
  public BooleanExpression withGroupId(String groupId) {
    return query.group.id.equalsIgnoreCase(groupId);
  }
}