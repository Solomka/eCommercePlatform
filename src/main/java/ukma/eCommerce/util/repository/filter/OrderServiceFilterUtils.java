package ukma.eCommerce.util.repository.filter;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;

/**
 *  service -specific filters for OrderRepository
 * 
 * @author Solomka
 *
 */
public final class OrderServiceFilterUtils {

	private OrderServiceFilterUtils() {
		throw new RuntimeException();
	}

	public static OrderByIdFilter orderById(OrderID orderId){
		return new OrderByIdFilter(orderId);
	}

}
