package ukma.eCommerce.util.repository.filter;

import org.springframework.data.jpa.domain.Specification;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;

/**
 * repository-specific filters for OrderRepository
 * 
 * @author Solomka
 *
 */
public final class OrderRepoFilterUtils {

	private OrderRepoFilterUtils() {
		throw new RuntimeException();
	}

	public static Specification<OrderPO> findOrderById(OrderID orderId) {
		return (root, query, cb) -> {
			return cb.equal(root.get("id"), orderId.getId());
		};
	}

	public static Specification<OrderPO> findOrderByCustomerId(CustomerID customerId) {
		return (root, query, cd) -> {
			return cd.equal(root.get("customer").get("id"), customerId.getId());
		};
	}

	public static Specification<OrderPO> findOrderByStatus(OrderStatus orderStatus) {
		return (root, query, cd) -> {
			return cd.equal(root.get("status"), orderStatus);
		};

	}
	/*
	 * public static Specifications<OrderPO>
	 * findOrderByUserIdAndStatus(CustomerID customerId, OrderStatus
	 * orderStatus) { return
	 * Specifications.where(findOrderByCustomerId(customerId)).and(
	 * findOrderByStatus(orderStatus));
	 * 
	 * }
	 */

}
