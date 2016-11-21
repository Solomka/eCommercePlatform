package ukma.eCommerce.core.paymentModule.repository;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;

public final class OrderFilterUtils {

	private OrderFilterUtils() {
		throw new RuntimeException();
	}
/*
	public static OrderByIdFilter getOrderById(OrderID orderId) {
		return new OrderByIdFilter(orderId.getId());
	}
*/
	static Specification<OrderPO> findOrderById(OrderID orderId) {
		return (root, query, cb) -> {
			return cb.equal(root.get("id"), orderId.getId());
		};
	}

}
