package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class OrderRepository extends AHibernateRepository<Order, OrderID, OrderSaveDTO, IExposedFilter, OrderPO, UUID> {

	@Override
	public Observable<Collection<Order>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		//return Observable.just(f.toFilter()).map(ordersPO -> Observable.from(ordersPO)).flatMap(orderPO -> OrderPOConverter.toOrder(orderPO));
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
	
	/*
	public Observable<OrderPO> saveOrderPO(OrderPO orderPO) {
		return Observable.just(save(orderPO)).map(uuid -> get(uuid));
	}
*/

}
