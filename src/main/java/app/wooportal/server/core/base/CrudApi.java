package app.wooportal.server.core.base;

import app.wooportal.server.core.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.dto.listing.PageableList;
import app.wooportal.server.core.error.exception.BadParamsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CrudApi<E extends BaseEntity, S extends DataService<E, ?>> {
  
  protected final static String params = "params";
  
  protected final static String entities = "entities";
  
  protected final static String entity = "entity";
  
  protected final static String id = "id";
  
  protected final static String ids = "ids";
  
  protected final S service;
  
  public CrudApi(S service) {
    this.service = service;
  }
  
  public PageableList<E> readAll(FilterSortPaginate params) {
    return service.readAll(params);
  }
  
  public Optional<E> readOne(E entity) {
    if (entity == null) {
      throw new BadParamsException("Empty entity");
    }
    
    return service.getByExample(entity);
  }
  
  public List<E> saveAll(List<E> entities) {
    if (entities == null || entities.isEmpty()) {
      throw new BadParamsException("entities is missing", entities);
    }
    
    return entities.parallelStream().map(this::saveOne)
        .distinct()
        .collect(Collectors.toList());
  }
  
  public E saveOne(E entity) {
    return entity != null
        ? service.saveWithContext(entity)
        : null;
  }
  
  public void deleteOne(String id) {
    if (id != null && !id.isEmpty()) {
      service.deleteById(id); 
    }
  }
  
  public void deleteAll(List<String> ids) {
    if (ids != null && !ids.isEmpty()) {
      service.deleteById(ids.toArray(String[]::new));
    }
  }
}
