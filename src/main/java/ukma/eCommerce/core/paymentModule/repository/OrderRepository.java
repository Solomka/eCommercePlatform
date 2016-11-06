package ukma.eCommerce.core.paymentModule.repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

public class OrderRepository implements IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Order, String, Order, String, IFilter<String>> {

	@Override
	public Observable<Collection<Order>> find(@Null IFilter<String> stringIFilter) {
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

}
