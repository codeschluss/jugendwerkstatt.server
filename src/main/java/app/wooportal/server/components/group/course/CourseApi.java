package app.wooportal.server.components.group.course;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class CourseApi extends CrudApi<CourseEntity, CourseService> {

  public CourseApi(CourseService CourseService) {
    super(CourseService);
  }

  @Override
  @GraphQLQuery(name = "getCourses")
  public PageableList<CourseEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getCourse")
  public Optional<CourseEntity> readOne(@GraphQLArgument(name = CrudApi.entity) CourseEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveCourses")
  public List<CourseEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<CourseEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveCourse")
  public CourseEntity saveOne(@GraphQLArgument(name = CrudApi.entity) CourseEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteCourses")
  public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    super.deleteAll(ids);
  }

  @GraphQLMutation(name = "deleteCourse")
  public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
    super.deleteOne(id);
  }

}


