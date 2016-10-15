package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeVO;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;
import ukma.eCommerce.util.filter.ChargeFilter;

public class ChargeRepository implements IRepository<Charge, String, ChargeVO, ChargeFilter> {

	@Override
	public void find(@Null ChargeFilter filter, @NotNull IRetrieveCallback<Collection<Charge>> callback) {

	}

	@Override
	public void create(@NotNull ChargeVO chargeVO, @NotNull IRetrieveCallback<Charge> callback) {

	}

	@Override
	public void delete(@NotNull String s, @NotNull IUpdateCallback callback) {

	}

	@Override
	public void update(@NotNull ChargeVO chargeVO, @NotNull String s, @NotNull IRetrieveCallback<Charge> callback) {

	}
}