package ukma.eCommerce.core.paymentModule.service;

import org.joda.time.Period;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface IChargeApplicationService {

    @NotNull
    Observable<Collection<Charge>> fetchCharges(@NotNull Period period);


    @NotNull
    Observable<Charge> createCharge(@NotNull CreditCardDTO creditCard, @NotNull ChargeDTO chargeDTO);

}
