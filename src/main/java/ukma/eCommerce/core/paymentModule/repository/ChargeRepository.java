package ukma.eCommerce.core.paymentModule.repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;
import ukma.eCommerce.util.filter.ChargeFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

public class ChargeRepository implements IRepository<Charge, String, Charge, ChargeFilter> {

	@Override
	public Observable<Collection<Charge>> find(@Null ChargeFilter filter) {
		return null;
	}

	@Override
	public Observable<Charge> create(@NotNull Charge charge) {
		return null;
	}

	@Override
	public Observable<Void> delete(@NotNull String s) {
		return null;
	}

	@Override
	public Observable<Charge> update(@NotNull Charge charge) {
		return null;
	}

	@Override
	public void find(@Null ChargeFilter filter, @NotNull IRetrieveCallback<Collection<Charge>> callback) {

	}

	@Override
	public void create(@NotNull Charge chargeVO, @NotNull IRetrieveCallback<Charge> callback) {

	}

	@Override
	public void delete(@NotNull String s, @NotNull IUpdateCallback callback) {

	}

	@Override
	public void update(@NotNull Charge chargeVO, @NotNull String s, @NotNull IRetrieveCallback<Charge> callback) {

	}
}