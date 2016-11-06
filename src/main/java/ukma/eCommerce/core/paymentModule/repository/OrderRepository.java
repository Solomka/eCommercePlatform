package ukma.eCommerce.core.paymentModule.repository;

import org.springframework.stereotype.Repository;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderEntity;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import javax.validation.constraints.NotNull;
import java.util.Collection;
@Repository
public class OrderRepository implements IRepository<Order, OrderID, OrderEntity, IExposedFilter> {

	@Override
	public Observable<Collection<Order>> find(IExposedFilter filter) {
		return null;
	}

	@Override
	public Observable<Order> create(@NotNull OrderEntity orderEntity) {
		return null;
	}

	@Override
	public Observable<Void> delete(@NotNull OrderID orderID) {
		return null;
	}

	@Override
	public Observable<Order> update(@NotNull Order order) {
		return null;
	}


}
