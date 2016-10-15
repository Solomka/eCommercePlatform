package ukma.eCommerce.core.paymentModule.domainLogic;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeVO;
import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.filter.ChargeFilter;

public class ChargeManager implements IChargeManager {

	@Override
	public void fetchCharges(@NotNull Seller seller, @Null ChargeFilter filter,
			IRetrieveCallback<Collection<Charge>> callback) {

	}

	@Override
	public void conductCharge(@NotNull ChargeVO charge, @NotNull IRetrieveCallback<Charge> callback) {

	}
}
