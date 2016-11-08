package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderEntity;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

@Repository("orderRepository")
public class OrderRepository extends AHibernateRepository<OrderPO, UUID> implements
		IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Order, OrderID, OrderEntity, IExposedFilter> {

	@Override
	public Observable<Collection<Order>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Order> create(OrderEntity orderEntity) {
		// TODO Auto-generated method stub

		persist(OrderPOConverter.fromOrderEntity(orderEntity));
		return null;
	}

	@Override
	public Observable<Order> update(Order order) {
		// TODO Auto-generated method stub

		update(OrderPOConverter.fromOrder(order));
		return null;
	}

	@Override
	public Observable<Void> delete(Order order) {
		// TODO Auto-generated method stub
		delete(order);
		return null;
	}

}
