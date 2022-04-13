package app.wooportal.server.components.jobad.company;

import org.springframework.stereotype.Service;

import com.querydsl.core.types.dsl.BooleanExpression;

import app.wooportal.server.core.base.PredicateBuilder;

@Service
public class CompanyPredicateBuilder extends PredicateBuilder<QCompanyEntity, CompanyEntity> {

	public CompanyPredicateBuilder() {
		super(QCompanyEntity.companyEntity);
	}

	@Override
	public BooleanExpression freeSearch(String term) {
		return query.name.likeIgnoreCase(term).or(query.mail.likeIgnoreCase(term)).or(query.phone.likeIgnoreCase(term))
				.or(query.website.likeIgnoreCase(term));
	}

	public BooleanExpression withName(String name) {
		return query.name.equalsIgnoreCase(name);
	}
}
