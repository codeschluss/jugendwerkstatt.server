package app.wooportal.server.components.jobad.company;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.dto.listing.PageableList;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class CompanyApi extends CrudApi<CompanyEntity, CompanyService> {

	public CompanyApi(CompanyService CompanyService) {
		super(CompanyService);
	}

	@Override
	@GraphQLQuery(name = "getCompanies")
	public PageableList<CompanyEntity> readAll(@GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
		return super.readAll(params);
	}

	@Override
	@GraphQLQuery(name = "getCompany")
	public Optional<CompanyEntity> readOne(@GraphQLArgument(name = CrudApi.entity) CompanyEntity entity) {
		return super.readOne(entity);
	}

	@Override
	@GraphQLMutation(name = "saveCompanies")
	public List<CompanyEntity> saveAll(@GraphQLArgument(name = CrudApi.entities) List<CompanyEntity> entities) {
		return super.saveAll(entities);
	}

	@Override
	@GraphQLMutation(name = "saveCompany")
	public CompanyEntity saveOne(@GraphQLArgument(name = CrudApi.entity) CompanyEntity entity) {
		return super.saveOne(entity);
	}

	@Override
	@GraphQLMutation(name = "deleteCompanies")
	public void deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
		super.deleteAll(ids);
	}

	@GraphQLMutation(name = "deleteCompany")
	public void delete(@GraphQLArgument(name = CrudApi.id) String id) {
		super.deleteOne(id);
	}

}
