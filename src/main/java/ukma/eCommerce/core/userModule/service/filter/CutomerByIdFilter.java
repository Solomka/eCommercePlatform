package ukma.eCommerce.core.userModule.service.filter;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.repository.filter.CustomerRepoFilterUtils;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

class CutomerByIdFilter implements IExposedFilter {

	private final CustomerID customerId;

	public CutomerByIdFilter(CustomerID customerId) {
		this.customerId = Objects.requireNonNull(customerId);
	}

	@Override
	public Specification<CustomerPO> toFilter() {
		return CustomerRepoFilterUtils.findCustomerById(customerId);
	}

}
