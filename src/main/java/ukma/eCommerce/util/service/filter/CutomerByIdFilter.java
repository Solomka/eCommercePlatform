package ukma.eCommerce.util.service.filter;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.util.repository.filter.CustomerRepoFilterUtils;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

class CutomerByIdFilter implements IExposedFilter {

	private final CustomerID customerId;

	public CutomerByIdFilter(CustomerID customerId) {
		this.customerId = customerId;
	}

	@Override
	public Specification<CustomerPO> toFilter() {
		// TODO Auto-generated method stub
		return CustomerRepoFilterUtils.findCustomerById(customerId);
	}

}
