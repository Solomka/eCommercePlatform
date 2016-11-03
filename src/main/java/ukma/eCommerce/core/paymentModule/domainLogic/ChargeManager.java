package ukma.eCommerce.core.paymentModule.domainLogic;

import com.stripe.Stripe;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.schedulers.Schedulers;
import ukma.eCommerce.core.paymentModule.domainLogic.repository.IStripeRepository;
import ukma.eCommerce.core.paymentModule.domainLogic.util.IChargeFactory;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
// TODO charge processing queue + synchronization
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
    public Observable<Charge> conductCharge(@NotNull Invoice invoice) {
        // conducts charge, using default customer's credit card
        return stripeRepository.find(invoice.getCustomer())
                .observeOn(Schedulers.newThread())
                .flatMap(usrStripeId -> {

                    try {

                        final Customer customer = Customer.retrieve(usrStripeId);
                        final Card source = (Card) customer.getSources().retrieve("{CARD_ID!}");
                        final Map<String, Object> chargeParams = new HashMap<>();
                        // charge parameters preparation
                        chargeParams.put(ChargeManager.AMOUNT_FIELD, invoice.getPrice().getAmount());
                        chargeParams.put(ChargeManager.CURRENCY_FIELD, invoice.getPrice().getCurrency().getShortName());
                        chargeParams.put(ChargeManager.SOURCE_FIELD, source);
                        com.stripe.model.Charge.create(chargeParams);

                        return Observable.just(source);
                    } catch (final Exception e) {
                        // return 'failed' charge instead of error
                        return Observable.error(e);
                    }
                })
                // transform into charge instance
                .flatMap(stripeCard -> {
                    final DateTime expDate = new DateTime(stripeCard.getExpYear(), stripeCard.getExpMonth(), 1, 0, 0);
                    // no card number, no cvv, should be removed?
                    return Observable.just(chargeFactory.create(invoice, new CreditCard(stripeCard.getLast4(), "cvv", expDate)));
                });
    }

    @Override
    public Observable<Charge> conductCharge(@NotNull Invoice invoice, @NotNull CreditCard creditCard) {
        // conducts charge, using default customer's credit card
        return Observable.create((Observable.OnSubscribe<Charge>) subscriber -> {

            try {

                final Map<String, Object> sourceParams = new HashMap<>();
                final Map<String, Object> chargeParams = new HashMap<>();
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

                subscriber.onNext(chargeFactory.create(invoice, creditCard));
                subscriber.onCompleted();
            } catch (final Exception e) {
                // return 'failed' charge instead of error
                subscriber.onError(e);
            }
        }).observeOn(Schedulers.newThread());
    }

}
