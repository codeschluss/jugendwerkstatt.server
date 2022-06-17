package app.wooportal.server.core.base;

import javax.persistence.EntityGraph;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import app.wooportal.server.core.utils.SortPageUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

@Getter
@ToString
public class Query<E extends BaseEntity> {

  private Class<E> entityClass;

  private EntityGraph<E> graph;

  private GraphBuilder<E> graphBuilder;

  private Integer limit;

  private PageRequest page;

  private BooleanBuilder predicateBuilder;

  private Sort sort;

  private Query() {}

  public static <E extends BaseEntity> Query<E> init(
      Class<E> entityClass, GraphBuilder<E> graphBuilder) {
    return init(entityClass, graphBuilder, false);
  }

  public static <E extends BaseEntity> Query<E> init(
      Class<E> entityClass, GraphBuilder<E> graphBuilder, boolean defaultSort) {
    var instance = new Query<E>();
    var builder = new BooleanBuilder();

    instance.setEntityClass(entityClass);
    instance.setGraphBuilder(graphBuilder);
    instance.setPredicateBuilder(builder);

    if (defaultSort) {
      SortPageUtils.createDefaultSort(entityClass).ifPresent(instance::setSort);
    }
    return instance;
  }

  protected Query<E> setEntityClass(Class<E> entityClass) {
    this.entityClass = entityClass;
    return this;
  }
  
  public Query<E> sort(String... properties) {
    return sort(Direction.ASC, properties);
  }
  
  public Query<E> sort(Direction dir, String... properties) {
    return setSort(SortPageUtils.createSort(dir, properties));
  }

  protected Query<E> setSort(Sort sort) {
    this.sort = sort;

    if (this.page != null) {
      this.page = PageRequest.of(this.page.getPageNumber(), this.page.getPageSize(), this.sort);
    }

    addGraph(sort);
    return this;
  }

  public Query<E> addGraph(Sort sort) {
    return addGraph(
        graphBuilder.create(
            entityClass, sort.get().map(Order::getProperty).toArray(String[]::new)));
  }

  public Query<E> addGraph(EntityGraph<E> graph) {
    if (graph != null) {
      this.graph = this.graph == null ? graph : graphBuilder.merge(entityClass, this.graph, graph);
    }
    return this;
  }

  private Query<E> setGraphBuilder(GraphBuilder<E> graphBuilder) {
    this.graphBuilder = graphBuilder;
    return this;
  }

  public Query<E> setLimit(Integer limit) {
    this.limit = limit;
    return this;
  }

  public Query<E> setPage(PageRequest page) {
    this.page = page;

    if (this.sort != null && (page.getSort() == null || page.getSort().isUnsorted())) {
      this.page = PageRequest.of(this.page.getPageNumber(), this.page.getPageSize(), this.sort);
    }
    return this;
  }

  public Predicate getPredicate() {
    return this.predicateBuilder;
  }

  private Query<E> setPredicateBuilder(BooleanBuilder builder) {
    this.predicateBuilder = builder;
    return this;
  }

  public Query<E> and(Predicate predicate) {
    this.predicateBuilder.and(predicate);
    return this;
  }

  public Query<E> or(Predicate predicate) {
    this.predicateBuilder.or(predicate);
    return this;
  }
}
