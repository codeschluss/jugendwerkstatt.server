package app.wooportal.server.components.event.base;

import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import app.wooportal.server.components.event.organizer.OrganizerService;
import app.wooportal.server.components.event.schedule.ScheduleEntity;
import app.wooportal.server.components.event.schedule.ScheduleService;
import app.wooportal.server.components.location.address.AddressService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {

  public EventService(DataRepository<EventEntity> repo, EventPredicateBuilder predicate,
      ScheduleService scheduleService, OrganizerService organizerService,
      AddressService addressService, MediaService mediaService) {
    super(repo, predicate);

    addService("schedules", scheduleService);
    addService("organizer", organizerService);
    addService("address", addressService);
    addService("titleImage", mediaService);
    addService("images", mediaService);
  }

  @Override
  public Optional<EventEntity> getExisting(EventEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<EventEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }

  public Optional<ScheduleEntity> getNextSchedule(EventEntity event) {
    var scheduleService = getService(ScheduleService.class);
    var schedules = scheduleService.readAll(scheduleService.query()
        .and(scheduleService.getPredicate().withStartDateAfter(OffsetDateTime.now())
            .and(scheduleService.getPredicate().withEvent(event)))
        .sort("startDate").setLimit(1)).getList();

    return !schedules.isEmpty() ? Optional.of(schedules.get(0)) : Optional.empty();
  }

  @Override
  protected void preSave(EventEntity entity, EventEntity newEntity, JsonNode context) {

    if (newEntity.getTitleImage() == null 
        && !newEntity.getImages().isEmpty()
        && newEntity.getImages() != null) {
      newEntity.setTitleImage(newEntity.getImages().get(0));
      setContext("titleImage", context);
    }
  }
}
