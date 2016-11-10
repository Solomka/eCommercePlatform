package ukma.eCommerce.core.paymentModule.domainLogic;


import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;

import javax.validation.constraints.NotNull;

/**
 * Base charge manager contract to provide charges
 *
 * @author Max
 */
public interface IChargeManager {

    /**
     * Conducts charge using specified instance of {@linkplain Invoice}. In this
     * case to pay order default user's card will be used (if any)
     *
     * @param invoice fulfilled instance of {@linkplain Invoice}
     * @return instance of {@linkplain Observable} to monitor charging process
     * @throws NullPointerException if null was passed instead of invoice instance
     */
    @NotNull
    Observable<ChargeSaveDTO> conductCharge(@NotNull Invoice invoice);

    /**
     * Conducts charge using specified instance of {@linkplain Invoice} and specified credit card
     * instance
     *
     * @param invoice    fulfilled instance of {@linkplain Invoice}
     * @param creditCard credit card to conduct charge
     * @return instance of {@linkplain Observable} to monitor charging process
     * @throws NullPointerException if null was passed instead of invoice instance
     */
    @NotNull
    Observable<ChargeSaveDTO> conductCharge(@NotNull Invoice invoice, @NotNull CreditCard creditCard);

}
