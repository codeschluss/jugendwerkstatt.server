package app.wooportal.server.components.event;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.address.AddressService;
import app.wooportal.server.components.organizer.OrganizerService;
import app.wooportal.server.components.schedule.ScheduleService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.image.ImageService;

@Service
public class EventService extends DataService<EventEntity, EventPredicateBuilder> {



  public EventService(
      EventRepository repo,
      EventPredicateBuilder predicate,
      ScheduleService scheduleService,
      OrganizerService organizerService,
      AddressService addressService,
      ImageService imageService) {
    super(repo, predicate);
    
    addService("schedules", scheduleService);
    addService("organizer", organizerService);
    addService("address", addressService);
    addService("title_image", imageService);
  }

  @Override
  public Optional<EventEntity> getExisting(EventEntity entity) {
    return entity.getName() == null || entity.getName().isEmpty() ? Optional.empty()
        : getByName(entity.getName());
  }

  public Optional<EventEntity> getByName(String name) {
    return repo.findOne(predicate.withName(name));
  }
}
