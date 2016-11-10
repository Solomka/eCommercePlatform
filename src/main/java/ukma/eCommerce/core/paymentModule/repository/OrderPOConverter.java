package ukma.eCommerce.core.paymentModule.repository;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;

/**
 * class for Repository OrderPO and Business Logic Order mapping
 * 
 * created on 11/09/16
 * @author Solomka
 *
 */
final class OrderPOConverter {

	private static final ModelMapper MAPPER = new ModelMapper();

	private OrderPOConverter() {
		throw new RuntimeException();
	}

	/**
	 * convert from OrderEntity to OrderPO
	 * 
	 * @param orderEntity
	 * @return
	 */
	static OrderPO fromOrderEntity(@NotNull OrderSaveDTO orderEntity) {

		return null;
	}

	/**
	 * convert from Order to OrderPO
	 * 
	 * @param order
	 * @return
	 */

	static OrderPO fromOrder(@NotNull Order order) {
		return MAPPER.map(order, OrderPO.class);
	}

	/**
	 * convert from OrderPO to Order
	 * 
	 * @param orderPO
	 * @return
	 */
	static Order toOrder(@NotNull OrderPO orderPO) {
		return MAPPER.map(orderPO, Order.class);
	}

}
