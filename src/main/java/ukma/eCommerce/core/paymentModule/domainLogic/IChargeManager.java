package ukma.eCommerce.core.paymentModule.domainLogic;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.filter.ChargeFilter;

/**
 * Core payment business logic contract
 * 
 * @author Max
 */
public interface IChargeManager {
	/**
	 * Fetches user specific charge statistic asynchronously. Result can be
	 * handled via supplied callback
	 *
	 */
	void fetchCharges(@NotNull Seller seller, @Null ChargeFilter filter, IRetrieveCallback<Collection<Charge>> callback);

	// add whatever you need
	// ......

	void conductCharge(@NotNull Charge charge, @NotNull IRetrieveCallback<Charge> callback);

}
