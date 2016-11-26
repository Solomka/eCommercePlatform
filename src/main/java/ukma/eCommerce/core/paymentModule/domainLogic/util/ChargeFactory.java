package ukma.eCommerce.core.paymentModule.domainLogic.util;

import org.springframework.stereotype.Component;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Created by Максим on 11/3/2016.
 */
@Component
public final class ChargeFactory implements IChargeFactory {

	/**
	 * AR should always be consistent (id can not be NULL)
	 */

	@Override
	public ChargeSaveDTO create(@NotNull Invoice invoice, @Null ChargeStatus status) {
		return new ChargeSaveDTO.Builder().build();
	}

	@Override
	public ChargeSaveDTO create(@NotNull Invoice invoice) {
		return create(invoice, null);
	}
}
