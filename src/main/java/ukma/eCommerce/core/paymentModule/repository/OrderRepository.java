package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderVO;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;
import ukma.eCommerce.util.filter.OrderFilter;

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
