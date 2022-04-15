package app.wooportal.server.components.jobad.base;

import org.springframework.stereotype.Service;
import app.wooportal.server.components.jobad.company.CompanyService;
import app.wooportal.server.components.jobad.jobtype.JobTypeService;
import app.wooportal.server.core.base.DataService;
import app.wooportal.server.core.repository.DataRepository;

@Service
public class JobAdService extends DataService<JobAdEntity, JobAdPredicateBuilder> {

  public JobAdService(
      DataRepository<JobAdEntity> repo, 
      JobAdPredicateBuilder predicate,
      JobTypeService jobTypeService, CompanyService companyService) {
    super(repo, predicate);

    addService("company", companyService);
  }

}
