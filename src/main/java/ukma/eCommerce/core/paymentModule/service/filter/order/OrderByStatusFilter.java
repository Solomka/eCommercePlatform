package ukma.eCommerce.core.paymentModule.service.filter.order;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.paymentModule.repository.filter.OrderRepoFilterUtils;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

class OrderByStatusFilter implements IExposedFilter {

	private final OrderStatus orderStatus;

	public OrderByStatusFilter(OrderStatus orderStatus) {
		this.orderStatus = Objects.requireNonNull(orderStatus);
	}

	@Override
	public Specification<OrderPO> toFilter() {
		return OrderRepoFilterUtils.findOrderByStatus(orderStatus);
	}

}
