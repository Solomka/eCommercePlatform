package ukma.eCommerce.core.paymentModule.domainLogic;

import com.stripe.Stripe;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.schedulers.Schedulers;
import ukma.eCommerce.core.paymentModule.domainLogic.repository.IStripeRepository;
import ukma.eCommerce.core.paymentModule.domainLogic.repository.StripeFilter;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.util.IReadonlyRepository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChargeManager implements IChargeManager {

    static {
        Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
    }

    private final IReadonlyRepository<ukma.eCommerce.core.userModule.model.domain.bo.Customer, CustomerFilter> repository;
    private final IStripeRepository stripeRepository;

    @Autowired
    public ChargeManager(IReadonlyRepository<ukma.eCommerce.core.userModule.model.domain.bo.Customer, CustomerFilter> repository, IStripeRepository stripeRepository) {
        this.repository = repository;
        this.stripeRepository = stripeRepository;
    }

    @Override
    public Observable<Charge> conductCharge(@NotNull Invoice invoice) {

        return repository.find(new CustomerFilter().withInvoiceId(invoice.getId()))
                .observeOn(Schedulers.newThread())
                .flatMap(customers -> {
                    if (customers.isEmpty())
                        return Observable.error(new Exception("No customer found"));

                    if (customers.size() > 1)
                        return Observable.error(new Exception("More than one customer with given invoice id"));

                    return Observable.just(customers.iterator().next());
                }).flatMap(customer -> stripeRepository.find(new StripeFilter().setCustomerID(customer.getId())))
                .flatMap(stripeIds -> {

                    if (stripeIds.isEmpty())
                        return Observable.error(new Exception("No stripe id with associated customer"));

                    if (stripeIds.size() > 1)
                        return Observable.error(new Exception("More than one stripe id associated with given customer"));

                    return Observable.just(stripeIds.iterator().next());
                })
                .flatMap(stripeId -> {

                    try {

                        final Customer stripeCustomer = Customer.retrieve(stripeId);
                        final Map<String, Object> chargeParams = new HashMap<>();

                        chargeParams.put("amount", invoice.getPrice().getAmount());
                        chargeParams.put("currency", invoice.getPrice().getCurrency().getShortName());
                        chargeParams.put("customer", stripeCustomer.getId());

                        return Observable.just(com.stripe.model.Charge.create(chargeParams));
                    } catch (final Exception e) {
                        return Observable.error(e);
                    }
                }).flatMap(stripeCharge -> Observable.just(null));
    }

    /**
     * final Observable<Collection<ukma.eCommerce.core.userModule.model.domain.bo.Customer>> result =
     repository.find(new CustomerFilter().withInvoiceId(invoice.getId()));

     result.subscribe(customers -> {

     if(customers.isEmpty());// error
     if(customers.size() > 1);//error

     final ukma.eCommerce.core.userModule.model.domain.bo.Customer customer = customers.iterator().next();

     Observable<Collection<String>> stripeObs =
     stripeRepository.find(new StripeFilter().setCustomerID(customer.getId()));

     stripeObs.subscribe(ids -> {

     if(ids.isEmpty());// error
     if(ids.size() > 1);//error

     final String stripeId = ids.iterator().next();

     try {
     final Customer stripeCustomer = Customer.retrieve(stripeId);
     final Map<String, Object> chargeParams = new HashMap<>();

     chargeParams.put("amount", invoice.getPrice().getAmount());
     chargeParams.put("currency", invoice.getPrice().getCurrency().getShortName());
     chargeParams.put("customer", stripeCustomer.getId());

     com.stripe.model.Charge charge = com.stripe.model.Charge.create(chargeParams);
     } catch (final Exception e) {
     e.printStackTrace();
     }

     }, th -> {

     });

     }, th -> {});
     */

}
