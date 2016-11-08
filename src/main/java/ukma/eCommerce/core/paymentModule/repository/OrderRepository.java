package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.HibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IFilter;

public class OrderRepository extends HibernateRepository<OrderPO, UUID> implements
		IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Order, UUID, OrderPO, String, IFilter<String>> {

	@Override
	public Observable<Collection<Order>> find(IFilter<String> f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Order> create(OrderPO order) {
		// TODO Auto-generated method stub

		// create orderPO on base of Order
		OrderPO orderPO = new OrderPO();
		persist(orderPO);
		return null;
	}

	@Override
	public Observable<Void> delete(UUID key) {
		// TODO Auto-generated method stub
		delete(key);
		return null;
	}

	@Override
	public Observable<Order> update(Order order) {
		// TODO Auto-generated method stub
		
		// create orderPO on base of Order
		OrderPO orderPO = new OrderPO();
		update(orderPO);
		return null;
	}

}
