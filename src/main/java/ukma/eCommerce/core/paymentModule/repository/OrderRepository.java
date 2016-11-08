package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderEntity;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.HibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

@Repository("orderRepository")
public class OrderRepository extends HibernateRepository<OrderPO, UUID> implements
		IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Order, OrderID, OrderEntity, IExposedFilter> {

	@Override
	public Observable<Collection<Order>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Order> create(OrderEntity e) {
		// TODO Auto-generated method stub

		// create orderPO on base of Order
		OrderPO orderPO = new OrderPO();
		persist(orderPO);
		return null;
	}

	@Override
	public Observable<Void> delete(OrderID key) {
		// TODO Auto-generated method stub
		delete(key);
		return null;
	}

	@Override
	public Observable<Order> update(Order t) {
		// TODO Auto-generated method stub

		// create orderPO on base of Order
		OrderPO orderPO = new OrderPO();
		update(orderPO);
		return null;
	}

}
