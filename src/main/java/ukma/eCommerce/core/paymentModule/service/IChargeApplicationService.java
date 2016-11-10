package ukma.eCommerce.core.paymentModule.service;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.joda.time.Period;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeExecutionDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;

public interface IChargeApplicationService {

	@NotNull
	Observable<Collection<Charge>> fetchCharges(@NotNull Period period);

	@NotNull
	Observable<ChargeExecutionDTO> createCharge(@NotNull CreditCardDTO creditCard, @NotNull ChargeDTO chargeDTO);

}
