package ukma.eCommerce.util.repository.filter;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.repository.OrderRepoFilterUtils;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;

public class OrderByIdFilter implements IExposedFilter {

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
