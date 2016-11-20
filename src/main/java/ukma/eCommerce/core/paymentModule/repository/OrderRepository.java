package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
@Repository("orderRepository")
public class OrderRepository extends AHibernateRepository<Order, OrderID, OrderSaveDTO, IExposedFilter, OrderPO, UUID> {

	@Override
	public Observable<Collection<Order>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Order> create(OrderSaveDTO orderSaveDTO) {
		return Observable.just(save(OrderPOConverter.fromOrderSaveDTO(orderSaveDTO))).map(uuid -> get(uuid))
				.map(po -> OrderPOConverter.toOrder(po));
	}
	
	@Override
	public Observable<Boolean> delete(OrderID orderID) {
		//return Observable.create(subscriber -> getSession().delete((OrderPO) loadEntity(k)));
		return Observable.just(deleteEntity(orderID.getId()));
	}

	@Override
	public Observable<Order> update(Order order) {
		return Observable.create(subsciber ->{
		updateEntity(OrderPOConverter.fromOrder(order));
		OrderPOConverter.toOrder(get(order.getId().getId()));
	
	});}

}
