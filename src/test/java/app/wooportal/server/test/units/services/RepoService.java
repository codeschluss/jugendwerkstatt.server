package app.wooportal.server.test.units.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import org.hibernate.PropertyValueException;
import com.querydsl.core.types.Predicate;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.Query;
import app.wooportal.server.core.dto.listing.PageableList;
import app.wooportal.server.core.repository.DataRepository;
import app.wooportal.server.core.utils.ReflectionUtils;
import lombok.Setter;

@Setter
public class RepoService<E extends BaseEntity> implements DataRepository<E> {

  private List<E> data;
  
  public RepoService() {
    init(null);
  }
  
  public RepoService(List<E> data) {
    init(data);
  }
  
  private void init(List<E> data) {
    this.data = new ArrayList<>();
    if (data != null) {
      this.data.addAll(data); 
    }
  }

  public List<E> findAll() {
    return data;
  }
  
  public PageableList<E> findAll(Query<E> query) {
    PageableList<E> result = query.getPredicate() != null
        ? new PageableList<E>(find(query.getPredicate()))
        : new PageableList<E>(data);
    
    if (query.getLimit() != null) {
      result.setResult(result.getList().subList(0, query.getLimit()));
    }
    
    return result;
  }

  public Optional<E> findOne(Predicate predicate) {
    var result = find(predicate);
    
    return Optional.ofNullable(result != null && !result.isEmpty()
        ? result.get(0)
        : null);
  }
  
  private List<E> find(Predicate predicate) {
    return data.stream().filter(entity -> {
      try {
        QueryDslAssertion.assertPredicate(predicate, entity);
        return true;
      } catch (Throwable t) {
        return false;
      }
    }).collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  public <S extends E> List<S> saveAll(Iterable<S> entities) {
    entities.forEach(e -> {
      save(e);
    });
    
    return (List<S>) data;
  }

  public void deleteById(String id) {
    data.removeIf(e -> e.getId().equals(id));
  }

  public <S extends E> S save(S entity) {
    if (data == null) {
      data = new ArrayList<>();
    }
    
    constraintCheck(entity);
    
    if (!data.contains(entity)) {
      data.add(entity);
    } else {
      data.remove(entity);
      data.add(entity);
    }
    return entity;
  }
  
  private <S extends E> void constraintCheck(S entity) {
    for (var field: ReflectionUtils.getFields(entity.getClass())) {
      var fieldValue = ReflectionUtils.get(field.getName(), entity);
      nullableConstraintCheck(entity.getClass().getName(), field, fieldValue);
      uniqueConstraintCheck(entity.getClass().getName(), field, fieldValue);
    }
  }

  private void nullableConstraintCheck(
      String entityName,
      Field field, 
      Optional<Object> fieldValue) {
    if (ReflectionUtils.getAnnotation(field, JoinColumn.class).isPresent()
        && ReflectionUtils.getAnnotation(field, JoinColumn.class).get().nullable()
        && fieldValue.isEmpty()) {
      throw new PropertyValueException(
          "not-null property references a null or transient value",
          entityName,
          field.getName());
    }
    
    if (ReflectionUtils.getAnnotation(field, Column.class).isPresent() 
        && ReflectionUtils.getAnnotation(field, Column.class).get().nullable()
        && fieldValue.isEmpty()) {
      throw new PropertyValueException(
          "not-null property references a null or transient value",
          entityName,
          field.getName());
    }
  }
  
  private void uniqueConstraintCheck(
      String entityName,
      Field field, 
      Optional<Object> fieldValue) {
    var columnAnnotation = ReflectionUtils.getAnnotation(field, Column.class);
    if (fieldValue.isPresent() && columnAnnotation.isPresent() && columnAnnotation.get().unique()
        && data.stream().anyMatch(element -> {
          var result = ReflectionUtils.get(field.getName(), element);
          return result.isPresent() && result.get().equals(fieldValue.get());
        })) {
      throw new PropertyValueException(
          "unique property is not unique",
          entityName,
          field.getName());
    }
  }

  public Optional<E> findById(String id) {
    for (var element : data) {
      if (element.getId().equals(id)) {
        return Optional.of(element);
      }
    }
    return Optional.empty();
  }

  public boolean existsById(String id) {
    return findById(id).isPresent();
  }

  public Iterable<E> findAllById(Iterable<String> ids) {
    var result = data.stream().filter(element -> {
      for (var id : ids) {
        if (element.getId().equals(id)) {
          return true;
        }
      }
      return false;
    }).collect(Collectors.toList());
    
    return (Iterable<E>) result;
  }

  public long count() {
    return data != null ? data.size() : 0;
  }

  public void delete(E entity) {
    data.remove(entity);
  }

  public void deleteAll(Iterable<? extends E> entities) {
    if (entities != null) {
      for (var entity : entities) {
        data.remove(entity);
      }
    }
  }
  
  public void deleteAllById(Iterable<? extends String> ids) {
    data.removeIf(element -> {
      for (var id : ids) {
        if (element.getId().equals(id)) {
          return true;
        }
      }
      return false;
    });
  }

  public void deleteAll() {
    data = new ArrayList<>();
  }

  public boolean exists(Predicate predicate) {
    return findOne(predicate).isPresent();
  }
  
}
