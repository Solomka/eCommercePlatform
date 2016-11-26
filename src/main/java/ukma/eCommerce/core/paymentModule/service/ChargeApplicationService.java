package ukma.eCommerce.core.paymentModule.service;

import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rx.Observable;
import rx.schedulers.Schedulers;
import ukma.eCommerce.core.paymentModule.domainLogic.IChargeManager;
import ukma.eCommerce.core.paymentModule.domainLogic.exception.ChargeException;
import ukma.eCommerce.core.paymentModule.domainLogic.exception.ExternalApiException;
import ukma.eCommerce.core.paymentModule.domainLogic.util.IChargeFactory;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.dwo.*;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Solomka
 */
@Service
final class ChargeApplicationService implements IChargeApplicationService {

    private static final Logger LOGGER = Logger.getLogger(ChargeApplicationService.class.getName());

    private final IRepository<Charge, ChargeID, ChargeSaveDTO, IExposedFilter> chargeRepository;
    private final IRepository<Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter> invoiceRepository;
    private final IChargeManager chargeManager;
    private final IChargeFactory factory;

    @Autowired
    public ChargeApplicationService(
            @NotNull @Qualifier("chargeRepository") IRepository<Charge, ChargeID, ChargeSaveDTO, IExposedFilter> chargeRepository,
            @NotNull @Qualifier("invoiceRepository") IRepository<Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter> invoiceRepository,
            @NotNull IChargeManager chargeManager, @NotNull IChargeFactory factory) {
        this.chargeRepository = chargeRepository;
        this.invoiceRepository = invoiceRepository;
        this.chargeManager = chargeManager;
        this.factory = factory;
    }

    @Override
    public Observable<Collection<Charge>> fetchCharges(@NotNull Period period) {
        return null;
    }

	/*
     * TODO: correct usage of Observable -> DONE =)
	 * TODO: write invoice filter for getInvoiceByID -> NOT YET =(
	 * 
	 *
	 * 1. getInvoiceFromBDById 
	 * 2. try to conduct charge by means of Stripe 
	 * 3. if (success) then save charge to DB AND return message to controller(ChargeExecutionDTO) 
	 *    if (fail) then return message to controller (ChargeExecutionDTO) - ? - error handling by subscriber onError()
	 * 
	 * (non-Javadoc)
	 * 
	 * @see ukma.eCommerce.core.paymentModule.service.IChargeApplicationService#
	 * createCharge(ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO,
	 * ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO)
	 */

    @Override
    public Observable<ChargeExecutionDTO> createCharge(CreditCardDTO creditCardDTO, String invoiceID) {

        if ((!creditCardDTO.getClass().isAnnotationPresent(Validated.class)))
            throw new RuntimeException("@Validate annotation expected!");

        CreditCard creditCard = new CreditCard(creditCardDTO.getNumber(), creditCardDTO.getCvv(),
                creditCardDTO.getExpirationDate());

        return Observable
                .create((Observable.OnSubscribe<ChargeExecutionDTO>) subscriber -> {
                    //insteadOf null InvoiceByIdFilter
                    final Collection<Invoice> invoices = invoiceRepository.find(null);

                    if (invoices.isEmpty()) {
                        subscriber.onError(new ChargeException(
                                String.format("No invoice found with specified id, was %s", invoiceID)));
                        return;
                    }

                    final Invoice invoice = invoices.iterator().next();

                    try {
                        // conducts charge using specified payment parameters
                        chargeManager.conductCharge(new PaymentParams(invoice.getCustomer(), invoice.getPrice(), creditCard));

                        final Charge charge = chargeRepository.create(factory.create(invoice));

                        subscriber.onNext(new ChargeExecutionDTO("Charge is successfully conducted", charge.getStatus()));
                        subscriber.onCompleted();
                    } catch (final ExternalApiException e) {
                        LOGGER.log(Level.WARNING,
                                String.format("External api exception for invoice id %s", invoice.getId()), e);
                        subscriber.onError(e);
                    } catch (final Exception e) {
                        LOGGER.log(Level.SEVERE,
                                String.format("Internal exception for invoice id %s", invoice.getId()), e);
                        subscriber.onError(new RuntimeException("Internal service exception", e));
                    }
                }).subscribeOn(Schedulers.newThread());
		/*
         *
		 * // get invoice by id 
		 * // Invoice invoice = invoiceRepository.find(f);
		 * Invoice invoice = null; PaymentParams invoiceDTO = new
		 * PaymentParams.Builder().build();
		 * 
		 * 
		 * return Observable .create(subscriber ->
		 *	 chargeManager.conductCharge(invoiceDTO, creditCard) .subscribe(
		 *		 chargeEntity -> subscriber .add(chargeRepository.create(chargeEntity)
		 * 						.subscribe(
		 * 							charge ->
		 * 								 subscriber.onNext(new ChargeExecutionDTO(
		 *								 "Payment was conducted successfully", charge.getStatus())))),
		 *							th -> {
		 *								 LOGGER.log(Level.WARNING, String.format(
		 * 								"Error occurred while creating charge for chargeEntity"), th);
		 * 								subscriber.onError(new Exception("Failed to create charge")); 
		 * 									}));
		 */

    }
}
