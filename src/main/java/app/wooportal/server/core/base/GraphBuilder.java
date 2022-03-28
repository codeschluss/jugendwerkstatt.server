package app.wooportal.server.core.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.graph.EntityGraphs;
import org.hibernate.graph.Graph;
import org.hibernate.graph.RootGraph;
import org.springframework.stereotype.Service;
import app.wooportal.server.core.utils.ReflectionUtils;
import graphql.language.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GraphBuilder<E extends BaseEntity> {
  
  @PersistenceContext
  protected EntityManager entityManager;
  
  @SuppressWarnings("unchecked")
  public RootGraph<E> create(Class<E> entityClass, List<Field> context) {
    return entityClass != null && context != null && hasSubgraphs(context)
        ? (RootGraph<E>) create(entityClass, context, (Graph<E>) entityManager.createEntityGraph(entityClass))
        : null;
  }

  private boolean hasSubgraphs(List<Field> context) {
    for (Field field : context) {
      if (hasSubgraphs(field)) {
        return true;
      }
    }
    return false;
  }

  public Graph<E> create(
      Class<?> entityClass,
      List<Field> context,
      Graph<E> graph) {
    for (var field : context) {
      Optional<Class<?>> fieldType = getEntityType(entityClass, field.getName());
      if (fieldType.isPresent() 
          && isValidField(entityClass, field.getName())) {
        if (hasSubgraphs(field)) {
          create(
              fieldType.get(), 
              field.getSelectionSet().getSelectionsOfType(Field.class), 
              graph.addSubGraph(field.getName()));
        }
      }
    }
    return graph;
  }

  protected boolean isValidField(Class<?> entityClass, String fieldName) {
    return ReflectionUtils.getField(entityClass, fieldName).isPresent()
        && ReflectionUtils.getAnnotation(ReflectionUtils.getField(entityClass, fieldName).get(),
        Transient.class).isEmpty() && ReflectionUtils.getAnnotation(
        ReflectionUtils.getField(entityClass, fieldName).get(), ManyToMany.class).isEmpty();
  }

  public boolean hasSubgraphs(Field field) {
    return field.getSelectionSet() != null
        && field.getSelectionSet().getSelectionsOfType(Field.class) != null
        && !field.getSelectionSet().getSelectionsOfType(Field.class).isEmpty();
  }

  @SuppressWarnings("unchecked")
  public EntityGraph<E> create(Class<E> entityClass, String... paths) {
    if (hasSubgraphs(entityClass, paths)) {
      Graph<E> graph = (Graph<E>) entityManager.createEntityGraph(entityClass);
      for (String path : paths) {
        create(entityClass, graph, path);
      }    
      return (EntityGraph<E>) graph;
    }
    return null;
  }

  private boolean hasSubgraphs(Class<E> entityClass, String[] paths) {
    for (String path : paths) {
      var split = path.split("\\.");
      if (split.length > 1 
          || (split.length == 1 && getEntityType(entityClass, split[0]).isPresent())) {
        return true;
      }
    }
    return false;
  }

  @SuppressWarnings("unchecked")
  private void create(Class<E> entityClass, Graph<E> graph, String path) {
    String[] part = path.split("\\.", 2);
    
    if (part.length > 0 && getEntityType(entityClass, part[0]).isPresent()) {
      Graph<E> subgraph = graph.addSubGraph(part[0]);
      var fieldClass = getEntityType(entityClass, part[0]);
      if (part.length > 1 && fieldClass.isPresent()) {
        var fieldType = getEntityType(
            fieldClass.get(),
            part[1].split("\\.")[0]);
        if (fieldType.isPresent()) {
          create((Class<E>) fieldClass.get(), subgraph, part[1]);
        }
      }
    }
  }
  
  protected Optional<Class<?>> getEntityType(Class<?> clazz, String fieldName) {
    if (ReflectionUtils.isFieldTypeOfClass(clazz, fieldName, BaseEntity.class)) {
      return ReflectionUtils.getFieldType(clazz, fieldName);
    }
    
    if (ReflectionUtils.isFieldTypeOfClass(clazz, fieldName, Collection.class)) {
      return ReflectionUtils.getField(clazz, fieldName).map(collectionField ->
        (Class<?>) ((ParameterizedType) collectionField
          .getGenericType()).getActualTypeArguments()[0]);
    }
    
    return Optional.empty();
  }

  @SuppressWarnings("unchecked")
  public EntityGraph<E> merge(Class<E> entityClass, EntityGraph<E> graph1, EntityGraph<E> graph2) {
    return EntityGraphs.merge(entityManager.unwrap(SessionImplementor.class), entityClass, graph1, graph2);
  }
  
}
