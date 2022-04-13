package app.wooportal.server.components.jobad.base;

import java.util.Optional;
import org.springframework.stereotype.Service;
import app.wooportal.server.components.jobad.company.CompanyService;
import app.wooportal.server.components.jobad.jobtype.JobTypeService;
import app.wooportal.server.core.base.DataService;

@Service
public class JobAdService extends DataService<JobAdEntity, JobAdPredicateBuilder> {

	public JobAdService(JobAdRepository repo, JobAdPredicateBuilder predicate, JobTypeService jobTypeService,
			CompanyService companyService) {
		super(repo, predicate);

		addService("jobType", jobTypeService);
		addService("company", companyService);
	}

	@Override
	public Optional<JobAdEntity> getExisting(JobAdEntity entity) {
		return entity.getTitle() == null || entity.getTitle().isEmpty() ? Optional.empty()
				: getByTitle(entity.getTitle());
	}

	public Optional<JobAdEntity> getByTitle(String title) {
		return repo.findOne(predicate.withTitle(title));
	}
}
