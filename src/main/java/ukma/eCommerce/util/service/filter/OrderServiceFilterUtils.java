package ukma.eCommerce.util.service.filter;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * service - specific filters for OrderRepository
 * 
 * @author Solomka
 *
 */

public final class OrderServiceFilterUtils {

	private OrderServiceFilterUtils() {
		throw new RuntimeException();
	}

	public static IExposedFilter orderById(OrderID orderId) {
		return new OrderByIdFilter(orderId);
	}

	public static IExposedFilter orderByCustomerID(CustomerID customerId) {
		return new OrderByCustomerIdFilter(customerId);
	}

	public static IExposedFilter orderByStatus(OrderStatus orderStatus) {
		return new OrderByStatusFilter(orderStatus);
	}

}
