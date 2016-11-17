package ukma.eCommerce.core.paymentModule.repository;

import org.springframework.stereotype.Repository;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

/**
 * 
 * @author Solomka
 *
 */
@Repository("orderRepository")
public class OrderRepository extends AHibernateRepository<OrderPO, UUID> implements
		IRepository<Order, OrderID, OrderSaveDTO, IExposedFilter> {

	@Override
	public Collection<Order> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order create(OrderSaveDTO orderEntity) {
		// TODO Auto-generated method stub

		persist(OrderPOConverter.fromOrderEntity(orderEntity));
		return null;
	}

	@Override
	public Order update(Order order) {
		// TODO Auto-generated method stub

		updateEntity(OrderPOConverter.fromOrder(order));
		return null;
	}

	@Override
	public void delete(OrderID orderId) {
		// TODO Auto-generated method stub
		OrderPO orderPO = loadEntity(orderId.getId());
		if (Objects.nonNull(orderPO))
			deleteEntity(orderPO);
	}

}
