package ukma.eCommerce.core.paymentModule.repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> find(IExposedFilter f) {
				return findAllBySpecification((Specification<OrderPO>) f.toFilter()).stream()
				.map(orderPO ->OrderPOConverter.toOrder(orderPO)).collect(Collectors.toList());
	}
				
/*  @SuppressWarnings("unchecked")
    @Override
	public Observable<List<Order>> find(IExposedFilter f) {
		return Observable.just(findAllBySpecification((Specification<OrderPO>) f.toFilter()))
				.flatMap(ordersPO -> Observable.from(ordersPO))
				.flatMap(orderPO -> Observable.just(OrderPOConverter.toOrder(orderPO)))
				.buffer(500, TimeUnit.MILLISECONDS);
	}
*/	
	
	/*
	 * 
	 * public Observable<List<Order>> find(Specification<OrderPO> specification)
	 * {
	 * 
	 * return Observable.just(findAllBySpecification(specification))
	 * .flatMap(ordersPO ->Observable.from(ordersPO)) .flatMap(orderPO
	 * ->Observable.just(OrderPOConverter.toOrder(orderPO))).buffer(500,
	 * TimeUnit.MILLISECONDS);
	 * 
	 * }
	 */

	@Override
	public Order create(OrderSaveDTO orderSaveDTO) {
		OrderPO orderPO = OrderPOConverter.fromOrderSaveDTO(orderSaveDTO);
		savePO(orderPO);
		return OrderPOConverter.toOrder(orderPO);
			}

	/*
	@Override
	public Observable<Order> create(OrderSaveDTO orderSaveDTO) {
		return Observable.create(subsciber -> {
			OrderPO orderPO = OrderPOConverter.fromOrderSaveDTO(orderSaveDTO);
			savePO(orderPO);
			OrderPOConverter.toOrder(orderPO);
		});
	}
	*/
	
	@Override
	public void delete(OrderID orderId) {
		deletePOById(orderId.getId());		
	}
	
/*
	@Override
	public Observable<Boolean> delete(OrderID orderID) {
		// return Observable.create(subscriber -> getSession().delete((OrderPO)
		// loadEntity(k)));
		return Observable.just(deletePOById(orderID.getId()));
	}
	*/

	@Override
	public Order update(Order order) {
		OrderPO orderPO = updatePO(OrderPOConverter.fromOrder(order));
		return OrderPOConverter.toOrder(orderPO);
	}
/*
	@Override
	public Observable<Order> update(Order order) {
		return Observable.just(updatePO(OrderPOConverter.fromOrder(order)))
				.map(orderPO -> OrderPOConverter.toOrder(orderPO));
	}
*/
	
}
