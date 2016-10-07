package ukma.eCommerce.service;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.controller.vo.OrderVO;
import ukma.eCommerce.domain.bo.Order;
import ukma.eCommerce.util.IRetrieveCallback;

public interface OrderService {

	public void createOrder(@NotNull OrderVO orderVO, @NotNull IRetrieveCallback<Order> callback);

}
