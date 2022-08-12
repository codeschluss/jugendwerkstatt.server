package app.wooportal.server.components.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.documents.template.TemplateEntity;
import app.wooportal.server.components.documents.template.TemplateService;
import app.wooportal.server.components.event.base.EventEntity;
import app.wooportal.server.components.event.base.EventService;
import app.wooportal.server.components.jobad.base.JobAdEntity;
import app.wooportal.server.components.jobad.base.JobAdService;
import app.wooportal.server.core.base.BaseEntity;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;

@Service
public class SearchService {


  private final EventService eventService;
  private final TemplateService templateService;
  private final JobAdService jobAdService;

  public SearchService(EventService eventService, TemplateService templateService,
      JobAdService jobAdService) throws IOException {


    this.eventService = eventService;
    this.templateService = templateService;
    this.jobAdService = jobAdService;
  }

  public List<SearchDto> search(FilterSortPaginate params) {

    var list = new ArrayList<SearchDto>();

    var entities = new ArrayList<BaseEntity>();
    entities.addAll(eventService.readAll(params).getList());
    entities.addAll(jobAdService.readAll(params).getList());
    entities.addAll(templateService.readAll(params).getList());

    for (var entity : entities) {
      if (entity instanceof EventEntity) {
        var event = (EventEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(event.getId());
        searchResult.setTitle(event.getName());
        searchResult.setContent(event.getDescription());
        searchResult.setType(SearchResultType.event);
        list.add(searchResult);

      } else if (entity instanceof JobAdEntity) {
        var jobAd = (JobAdEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(jobAd.getId());
        searchResult.setTitle(jobAd.getTitle());
        searchResult.setContent(jobAd.getContent());
        searchResult.setType(SearchResultType.jobAd);
        list.add(searchResult);

      } else if (entity instanceof TemplateEntity) {
        var template = (TemplateEntity) entity;
        var searchResult = new SearchDto();

        searchResult.setId(template.getId());
        searchResult.setTitle(template.getName());
        searchResult.setContent(template.getContent());
        searchResult.setType(SearchResultType.template);
        list.add(searchResult);

      }
    }
    return params.getSize() != null && list.size() > params.getSize()
        ? list.subList(0, params.getSize())
        : list;
  }
}

