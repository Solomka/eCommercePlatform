package ukma.eCommerce.core.paymentModule.service;

import org.springframework.stereotype.Service;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.util.IRetrieveCallback;

import javax.validation.constraints.NotNull;

@Service("orderService")
public class OrderService implements IOrderService {

	//private IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Order, String, Order, OrderFilter> repository;

	@Override
	public void createOrder(@NotNull OrderDTO orderDTO, @NotNull IRetrieveCallback<ukma.eCommerce.core.paymentModule.model.domain.bo.Order> callback) {

	}
}
