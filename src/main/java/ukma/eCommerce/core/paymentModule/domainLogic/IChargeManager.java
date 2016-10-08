package ukma.eCommerce.core.paymentModule.domainLogic;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Seller;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeVO;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.filter.ChargeFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

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

	void conductCharge(@NotNull ChargeVO charge, @NotNull IRetrieveCallback<Charge> callback);

}
