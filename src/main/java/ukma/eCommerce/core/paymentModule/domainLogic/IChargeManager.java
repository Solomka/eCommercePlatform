package ukma.eCommerce.core.paymentModule.domainLogic;


import org.springframework.validation.annotation.Validated;
import ukma.eCommerce.core.paymentModule.domainLogic.exception.ExternalApiException;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.dwo.PaymentParams;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Base charge manager contract to provide charges
 *
 * @author Max
 */
@Validated
public interface IChargeManager {

    /**
     * Conducts charge using specified instance of {@linkplain PaymentParams} and credit card {@linkplain CreditCard}
     *
     * @param params     payment parameters such as payment destination, currency and so on
     * @throws NullPointerException if null was passed instead of invoice instance
     * @throws ExternalApiException if pay service is unavailable
     */
    void conductCharge(@NotNull @Valid PaymentParams params) throws ExternalApiException;

}
