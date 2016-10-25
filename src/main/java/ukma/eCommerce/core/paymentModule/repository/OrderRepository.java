package ukma.eCommerce.core.paymentModule.repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;
import ukma.eCommerce.util.filter.OrderFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

public class OrderRepository implements IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Order, String, Order, OrderFilter> {

	@Override
	public Observable<Collection<Order>> find(@Null OrderFilter filter) {
		return null;
	}

	@Override
	public Observable<Order> create(@NotNull Order order) {
		return null;
	}

	@Override
	public Observable<Void> delete(@NotNull String s) {
		return null;
	}

	@Override
	public Observable<Order> update(@NotNull Order order) {
		return null;
	}

	@Override
	public void find(OrderFilter filter, IRetrieveCallback<Collection<ukma.eCommerce.core.paymentModule.model.domain.bo.Order>> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(Order form, IRetrieveCallback<ukma.eCommerce.core.paymentModule.model.domain.bo.Order> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String key, IUpdateCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Order form, String key, IRetrieveCallback<ukma.eCommerce.core.paymentModule.model.domain.bo.Order> callback) {
		// TODO Auto-generated method stub

	}

}
