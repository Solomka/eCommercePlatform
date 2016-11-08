package ukma.eCommerce.core.paymentModule.service;

import org.joda.time.Period;
import org.springframework.stereotype.Service;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Created by Максим on 11/6/2016.
 */
@Service
final class  ChargeApplicationService implements IChargeApplicationService {

    @Override
    public Observable<Collection<Charge>> fetchCharges(@NotNull Period period) {
        return null;
    }

    @Override
    public Observable<Charge> createCharge(@NotNull CreditCardDTO creditCard, @NotNull ChargeDTO chargeDTO) {
        return null;
    }
}
