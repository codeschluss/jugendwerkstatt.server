package app.wooportal.server.test.units.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.query.QueryConjunction;
import app.wooportal.server.core.base.dto.query.QueryEntity;
import app.wooportal.server.core.base.dto.query.QueryExpression;
import app.wooportal.server.core.utils.ReflectionUtils;
import app.wooportal.server.test.units.core.entities.base.TestEntity;
import app.wooportal.server.test.units.core.entities.child.TestChildEntity;
import app.wooportal.server.test.units.core.entities.listChild.TestListChildEntity;

public class ObjectFactory {
  
  public static TestEntity newTestEntity(Map<String, Object> values) {
    return newInstance(TestEntity.class, values);
  }
  
  public static TestChildEntity newTestChildEntity(Map<String, Object> values) {
    return newInstance(TestChildEntity.class, values);
  }
  
  public static TestListChildEntity newTestListChildEntity(Map<String, Object> values) {
    return newInstance(TestListChildEntity.class, values);
  }
  
  public static FilterSortPaginate newFilter(Map<String, Object> values) {
    return newInstance(FilterSortPaginate.class, values);
  }
  
  public static QueryExpression newExp(Map<String, Object> values) {
    return newInstance(QueryExpression.class, values);
  }
  
  public static QueryEntity newQuery(Map<String, Object> values) {
    return newInstance(QueryEntity.class, values);
  }
  
  public static QueryConjunction newConjunction(Map<String, Object> values) {
    return newInstance(QueryConjunction.class, values);
  }
  
  public static <T> T newInstance(Class<T> clazz) {
    return newInstance(clazz, null);
  }
  
  public static <T> T newInstance(Class<T> clazz, Map<String, Object> values) {
    try {
      var obj = clazz.getDeclaredConstructor().newInstance();
      
      if (values != null && !values.isEmpty()) {
        for (Entry<String, Object> entry : values.entrySet()) {
          ReflectionUtils.set(entry.getKey(), obj, entry.getValue());
        }
      }
      return (T) obj;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
    }
    return null;
  }

}
