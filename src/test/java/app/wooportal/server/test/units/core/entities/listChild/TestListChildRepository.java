package app.wooportal.server.test.units.core.entities.listChild;

import org.springframework.data.repository.NoRepositoryBean;
import app.wooportal.server.core.repository.DataRepository;

@NoRepositoryBean
public interface TestListChildRepository extends DataRepository<TestListChildEntity> {

}
