package app.wooportal.server.components.jobad.base;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.jobad.company.CompanyService;
import app.wooportal.server.components.jobad.jobtype.JobTypeService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class JobAdService extends DataService<JobAdEntity, JobAdPredicateBuilder> {

  public JobAdService(DataRepository<JobAdEntity> repo, JobAdPredicateBuilder predicate,
      JobTypeService jobTypeService, CompanyService companyService) {
    super(repo, predicate);

    addService("company", companyService);
  }

  public List<JobAdEntity> withDueDates(OffsetDateTime... dates) {
    var query = query();
    for (var date : dates) {
      query.or(predicate.withDate(date));
    }
    return repo.findAll(query).getList();
  }
}
