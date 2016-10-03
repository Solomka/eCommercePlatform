package ukma.eCommerce.repository;

import java.util.Collection;

import ukma.eCommerce.controller.vo.OrderVO;
import ukma.eCommerce.domain.bo.Order;
import ukma.eCommerce.repository.filter.OrderFilter;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;

public class OrderRepository implements IRepository<Order, String, OrderVO, OrderFilter> {

	@Override
	public void find(OrderFilter filter, IRetrieveCallback<Collection<Order>> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(OrderVO form, IRetrieveCallback<Order> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String key, IUpdateCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(OrderVO form, String key, IRetrieveCallback<Order> callback) {
		// TODO Auto-generated method stub
		
	}

}
