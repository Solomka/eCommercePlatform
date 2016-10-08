package ukma.eCommerce.core.paymentModule.service;

import org.joda.time.Period;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;
import ukma.eCommerce.util.IRetrieveCallback;

import javax.validation.constraints.NotNull;
import java.util.Collection;

//translates value objects into corresponding method calls of payment service domain model
public interface IChargeService {

    void fetchCharges(@NotNull Period period, IRetrieveCallback<Collection<Charge>> callback);


    void createCharge(@NotNull CreditCardDTO creditCard, @NotNull ChargeDTO chargeDTO,
                      @NotNull IRetrieveCallback<Charge> callback);

}
