package app.wooportal.server.test.units.core.setup.entities.image;

import org.springframework.data.repository.NoRepositoryBean;
import app.wooportal.server.core.repository.DataRepository;

@NoRepositoryBean
public interface TestImageRepository extends DataRepository<TestImageEntity> {

}
