package ukma.eCommerce.core.paymentModule.service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.filter.OrderFilter;

@Service("orderService")
public class OrderService implements IOrderService {

	private IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Order, String, Order, OrderFilter> repository;

	@Override
	public void createOrder(@NotNull OrderDTO orderDTO, @NotNull IRetrieveCallback<ukma.eCommerce.core.paymentModule.model.domain.bo.Order> callback) {

	}
}
