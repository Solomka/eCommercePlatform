package ukma.eCommerce.core.paymentModule.service.filter.order;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.repository.filter.OrderRepoFilterUtils;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

class OrderByIdFilter implements IExposedFilter {

	private final OrderID orderId;

	public OrderByIdFilter(OrderID orderId) {
		this.orderId = Objects.requireNonNull(orderId);
	}

	@Override
	public Specification<OrderPO> toFilter() {
		return OrderRepoFilterUtils.findOrderById(orderId);
	}

}
