package ukma.eCommerce.core.paymentModule.domainLogic;

import org.springframework.stereotype.Component;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;

import javax.validation.constraints.NotNull;

@Component
public class ChargeManager implements IChargeManager {

	@Override
	public Observable<Charge> conductCharge(@NotNull Invoice invoice) {
		return null;
	}

}
