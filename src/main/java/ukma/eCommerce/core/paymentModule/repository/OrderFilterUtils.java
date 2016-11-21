package ukma.eCommerce.core.paymentModule.repository;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;

public final class OrderFilterUtils {

	private OrderFilterUtils() {
		throw new RuntimeException();
	}

	public static OrderByIdFilter getOrderById(OrderID orderId) {
		return new OrderByIdFilter(orderId.getId());
	}
	/*
	 * static Specification<Order> findOrderById(OrderID orderId) { return
	 * (root, query, cb) -> { String containsLikePattern =
	 * getContainsLikePattern(searchTerm); return cb.or(
	 * cb.like(cb.lower(root.<String>get(Todo_.title)), containsLikePattern),
	 * cb.like(cb.lower(root.<String>get(Todo_.description)),
	 * containsLikePattern) ); }; }
	 */

}
