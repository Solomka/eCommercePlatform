package ukma.eCommerce.service;

import org.springframework.stereotype.Service;

import ukma.eCommerce.domain.bo.Order;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	public Order createOrder() {
		return new Order();

	}

}
