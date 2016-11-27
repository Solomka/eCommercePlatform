package ukma.eCommerce.core.paymentModule.service.filter.order;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.repository.filter.OrderRepoFilterUtils;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

class OrderByCustomerIdFilter implements IExposedFilter {

	private final CustomerID customerId;

	public OrderByCustomerIdFilter(CustomerID customerId) {
		this.customerId = Objects.requireNonNull(customerId);
	}

	@Override
	public Specification<OrderPO> toFilter() {
		return OrderRepoFilterUtils.findOrderByCustomerId(customerId);
	}

}
