package ukma.eCommerce.util.service.filter;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.filter.IExposedFilter;
import ukma.eCommerce.util.repository.filter.OrderRepoFilterUtils;

class OrderByStatusFilter implements IExposedFilter {

	private final OrderStatus orderStatus;

	public OrderByStatusFilter(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public Specification<OrderPO> toFilter() {
		// TODO Auto-generated method stub
		return OrderRepoFilterUtils.findOrderByStatus(orderStatus);
	}

}
