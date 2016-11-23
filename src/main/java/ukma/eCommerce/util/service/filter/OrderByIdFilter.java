package ukma.eCommerce.util.service.filter;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.filter.IExposedFilter;
import ukma.eCommerce.util.repository.filter.OrderRepoFilterUtils;

class OrderByIdFilter implements IExposedFilter {

	private final OrderID orderId;

	public OrderByIdFilter(OrderID orderId) {
		this.orderId = orderId;
	}

	@Override
	public Specification<OrderPO> toFilter() {
		// TODO Auto-generated method stub
		return OrderRepoFilterUtils.findOrderById(orderId);
	}

}
