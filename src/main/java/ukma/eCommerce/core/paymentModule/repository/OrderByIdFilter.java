package ukma.eCommerce.core.paymentModule.repository;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

public final class OrderByIdFilter implements IExposedFilter {

	private CriteriaBuilder criteriaBuilder = null;
	private final CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
	private final Root invoice = criteriaQuery.from(OrderPO.class);

	private UUID id;

	public OrderByIdFilter(UUID id) {
		this.id = id;
	}

	@Override
	public Predicate toFilter() {
		return criteriaBuilder.equal(invoice.get("id"), id);
	}
}
