package ukma.eCommerce.util.service.filter;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.repository.filter.IExposedFilter;
import ukma.eCommerce.util.repository.filter.OrderRepoFilterUtils;

class OrderByCustomerIdFilter implements IExposedFilter {

	private final CustomerID customerId;

	public OrderByCustomerIdFilter(CustomerID customerId) {
		this.customerId = customerId;
	}

	@Override
	public Specification<OrderPO> toFilter() {
		// TODO Auto-generated method stub
		return OrderRepoFilterUtils.findOrderByCustomerId(customerId);
	}

}
