package app.wooportal.server.components.event.category;

import app.wooportal.server.core.repository.DataRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CategoryRepository extends DataRepository<CategoryEntity> {

}