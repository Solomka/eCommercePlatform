package ukma.eCommerce.service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import ukma.eCommerce.controller.vo.OrderVO;
import ukma.eCommerce.domain.bo.Order;
import ukma.eCommerce.repository.IRepository;
import ukma.eCommerce.repository.filter.OrderFilter;
import ukma.eCommerce.util.IRetrieveCallback;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private IRepository<Order, String, OrderVO, OrderFilter> repository;

	@Override
	public void createOrder(@NotNull OrderVO orderVO, @NotNull IRetrieveCallback<Order> callback) {
		repository.create(orderVO, callback);
	}

}
