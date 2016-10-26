package ukma.eCommerce.core.paymentModule.domainLogic;


import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;

import javax.validation.constraints.NotNull;

/**
 * Base charge manager contract to provide charges
 *
 * @author Max
 */
public interface IChargeManager {

    /**
     * Conducts charge using specified instance of {@linkplain Invoice}
     *
     * @param invoice fulfilled instance of {@linkplain Invoice}
     * @return instance of {@linkplain Observable} to monitor charging process
     * @throws NullPointerException if null was passed instead of invoice instance
     */
    @NotNull
    Observable<Charge> conductCharge(@NotNull Invoice invoice);

}
