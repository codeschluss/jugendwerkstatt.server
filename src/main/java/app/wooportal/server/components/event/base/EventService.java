package app.wooportal.server.components.event.base;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.event.organizer.OrganizerService;
import app.wooportal.server.components.event.schedule.ScheduleEntity;
import app.wooportal.server.components.event.schedule.ScheduleService;
import app.wooportal.server.components.location.address.AddressService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.media.base.MediaService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {

  private final ScheduleService scheduleService;

  public EventService(DataRepository<EventEntity> repo, EventPredicateBuilder predicate,
      ScheduleService scheduleService, OrganizerService organizerService,
      AddressService addressService, MediaService mediaService) {
    super(repo, predicate);

    addService("schedules", scheduleService);
    addService("organizer", organizerService);
    addService("address", addressService);
    addService("titleImage", mediaService);
    addService("images", mediaService);
    this.scheduleService = scheduleService;
  }

  @Override
  public Optional<EventEntity> getExisting(EventEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<EventEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }

  public ScheduleEntity getNextSchedule(EventEntity event) {

     var schedules = scheduleService.readAll(scheduleService.query()
     .and(scheduleService.getPredicate().withNotPastDates(OffsetDateTime.now())
     .and(scheduleService.getPredicate().withEvent(event)))).getList();

     Collections.sort(schedules, (o1, o2) -> o1.getStartDate().compareTo(o2.getStartDate()));
     
     if(!schedules.isEmpty()) {
       return schedules.get(0);
     }
    return null;  
  }
}
