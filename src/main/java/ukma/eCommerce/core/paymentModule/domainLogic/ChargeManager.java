package ukma.eCommerce.core.paymentModule.domainLogic;

import com.stripe.Stripe;
import com.stripe.exception.*;
import org.springframework.stereotype.Component;
import ukma.eCommerce.core.paymentModule.domainLogic.exception.ExternalApiException;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.dwo.PaymentParams;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public final class ChargeManager implements IChargeManager {

    // Stripe charge params fields
    private static final String AMOUNT_FIELD = "amount";
    private static final String CURRENCY_FIELD = "currency";
    private static final String SOURCE_FIELD = "source";
    private static final String EXP_MONTH_FIELD = "exp_month";
    private static final String EXP_YEAR_FIELD = "exp_year";
    private static final String NUMBER_FIELD = "number";
    private static final String CVC_FIELD = "cvc";
    private static final String CARD_SOURCE_FIELD = "object";
    private static final String CARD_SOURCE_FIELD_VAL = "card";

    private static final Logger LOGGER = Logger.getLogger(ChargeManager.class.getName());

    static {
        // just for test purposes
        Stripe.apiKey = "some key";
    }

    public ChargeManager() {
    }

    @Override
    public void conductCharge(@NotNull PaymentParams params) throws ExternalApiException {
        // conducts charge, using specified credit card
        try {

            final Map<String, Object> sourceParams = new HashMap<>(5);
            final Map<String, Object> chargeParams = new HashMap<>(3);
            final CreditCard creditCard = params.getCreditCard();
            // card preparation
            sourceParams.put(ChargeManager.EXP_YEAR_FIELD, creditCard.getExpirationDate().getYear());
            sourceParams.put(ChargeManager.EXP_MONTH_FIELD, creditCard.getExpirationDate().getMonthOfYear());
            sourceParams.put(ChargeManager.NUMBER_FIELD, creditCard.getNumber());
            sourceParams.put(ChargeManager.CVC_FIELD, creditCard.getCvv());
            sourceParams.put(ChargeManager.CARD_SOURCE_FIELD, ChargeManager.CARD_SOURCE_FIELD_VAL);
            // charge parameters preparation
            chargeParams.put(ChargeManager.AMOUNT_FIELD, params.getPrice().getAmount());
            chargeParams.put(ChargeManager.CURRENCY_FIELD, params.getPrice().getCurrency().getShortName());
            chargeParams.put(ChargeManager.SOURCE_FIELD, sourceParams);
            com.stripe.model.Charge.create(chargeParams);

        } catch (final APIConnectionException | CardException | InvalidRequestException
                | APIException | AuthenticationException e) {
            // error on the Stripe side
            LOGGER.log(Level.WARNING, "Internal stripe exception", e);
            throw new ExternalApiException(e);
        } catch (final Exception e) {
            // critical internal error
            LOGGER.log(Level.SEVERE, "Internal exception", e);
        }
    }

}
