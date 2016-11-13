package ukma.eCommerce.core.paymentModule.domainLogic;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.schedulers.Schedulers;
import ukma.eCommerce.core.paymentModule.domainLogic.repository.IStripeRepository;
import ukma.eCommerce.core.paymentModule.domainLogic.util.IChargeFactory;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoicePMDTO;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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

    private final IStripeRepository stripeRepository;
    private final IChargeFactory chargeFactory;

    @Autowired
    public ChargeManager(IStripeRepository stripeRepository, IChargeFactory chargeFactory) {
        this.stripeRepository = Objects.requireNonNull(stripeRepository);
        this.chargeFactory = chargeFactory;
    }

    @Override
    public Observable<ChargeSaveDTO> conductCharge(@NotNull InvoicePMDTO invoice) {
        // conducts charge, using default customer's credit card
        return stripeRepository.find(invoice.getCustomerID())
                .observeOn(Schedulers.newThread())
                .flatMap(usrStripeId -> {

                    try {

                        final Customer customer = Customer.retrieve(usrStripeId);
                        final Map<String, Object> chargeParams = new HashMap<>(3);
                        // charge parameters preparation
                        chargeParams.put(ChargeManager.AMOUNT_FIELD, invoice.getPrice().getAmount());
                        chargeParams.put(ChargeManager.CURRENCY_FIELD, invoice.getPrice().getCurrency().getShortName());
                        chargeParams.put(ChargeManager.SOURCE_FIELD, customer.getId());
                        com.stripe.model.Charge.create(chargeParams);

                        return Observable.just(chargeFactory.create(invoice));
                    } catch (final APIConnectionException | CardException | APIException | AuthenticationException e) {
                        // error on the Stripe side
                        LOGGER.log(Level.WARNING, "Internal stripe exception", e);
                        return Observable.just(chargeFactory.create(invoice, ChargeStatus.FAILED));
                    } catch (final Exception e) {
                        // critical internal error
                        LOGGER.log(Level.SEVERE, "Internal exception", e);
                        return Observable.error(e);
                    }
                });
    }

    @Override
    public Observable<ChargeSaveDTO> conductCharge(@NotNull InvoicePMDTO invoice, @NotNull CreditCard creditCard) {
        // conducts charge, using default customer's credit card
        return Observable.create((Observable.OnSubscribe<ChargeSaveDTO>) subscriber -> {

            try {

                final Map<String, Object> sourceParams = new HashMap<>(5);
                final Map<String, Object> chargeParams = new HashMap<>(3);
                // card preparation
                sourceParams.put(ChargeManager.EXP_YEAR_FIELD, creditCard.getExpirationDate().getYear());
                sourceParams.put(ChargeManager.EXP_MONTH_FIELD, creditCard.getExpirationDate().getMonthOfYear());
                sourceParams.put(ChargeManager.NUMBER_FIELD, creditCard.getNumber());
                sourceParams.put(ChargeManager.CVC_FIELD, creditCard.getCvv());
                sourceParams.put(ChargeManager.CARD_SOURCE_FIELD, ChargeManager.CARD_SOURCE_FIELD_VAL);
                // charge parameters preparation
                chargeParams.put(ChargeManager.AMOUNT_FIELD, invoice.getPrice().getAmount());
                chargeParams.put(ChargeManager.CURRENCY_FIELD, invoice.getPrice().getCurrency().getShortName());
                chargeParams.put(ChargeManager.SOURCE_FIELD, sourceParams);
                com.stripe.model.Charge.create(chargeParams);

                subscriber.onNext(chargeFactory.create(invoice));
            } catch (final APIConnectionException | CardException | APIException | AuthenticationException e) {
                // error on the Stripe side
                LOGGER.log(Level.WARNING, "Internal stripe exception", e);
                subscriber.onNext(chargeFactory.create(invoice, ChargeStatus.FAILED));
            } catch (final Exception e) {
                // critical internal error
                LOGGER.log(Level.SEVERE, "Internal exception", e);
                subscriber.onError(e);
            } finally {
                subscriber.onCompleted();
            }
        }).observeOn(Schedulers.newThread());
    }

}
