package app.wooportal.server.components.group.course;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Component;
import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import app.wooportal.server.core.security.permissions.ApprovedAndVerifiedPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
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
  @ApprovedAndVerifiedPermission
  public PageableList<CourseEntity> readAll(
      @GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
    return super.readAll(params);
  }

  @Override
  @GraphQLQuery(name = "getCourse")
  @ApprovedAndVerifiedPermission
  public Optional<CourseEntity> readOne(@GraphQLArgument(name = CrudApi.entity) CourseEntity entity) {
    return super.readOne(entity);
  }

  @Override
  @GraphQLMutation(name = "saveCourses")
  @AdminPermission
  public List<CourseEntity> saveAll(
      @GraphQLArgument(name = CrudApi.entities) List<CourseEntity> entities) {
    return super.saveAll(entities);
  }

  @Override
  @GraphQLMutation(name = "saveCourse")
  @AdminPermission
  public CourseEntity saveOne(@GraphQLArgument(name = CrudApi.entity) CourseEntity entity) {
    return super.saveOne(entity);
  }

  @Override
  @GraphQLMutation(name = "deleteCourses")
  @AdminPermission
  public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
    return super.deleteAll(ids);
  }

  @Override
  @GraphQLMutation(name = "deleteCourse")
  @AdminPermission
  public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
    return super.deleteOne(id);
  }
  
  @GraphQLQuery(name = "averageRating")
  @AdminPermission
  public CompletableFuture<Double> calculateAverageRating(@GraphQLContext CourseEntity course, Integer year) {
    return CompletableFuture.supplyAsync(() -> service.calculateAverageRating(course, year));
  }
  
  @GraphQLMutation(name = "addMember")
  @AdminPermission
  public boolean addMember(String courseId, String userId) {
    return service.addMember(courseId, userId);
  }
  
  @GraphQLMutation(name = "deleteMember")
  @AdminPermission
  public boolean deleteMember(String courseId, String userId) {
    return service.deleteMember(courseId, userId);
  }
}

