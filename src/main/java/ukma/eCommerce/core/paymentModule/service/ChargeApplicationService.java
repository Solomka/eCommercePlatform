package ukma.eCommerce.core.paymentModule.service;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;

import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.domainLogic.IChargeManager;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeExecutionDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoicePMDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceSaveDTO;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
@Service
final class ChargeApplicationService implements IChargeApplicationService {

	private static final Logger LOGGER = Logger.getLogger(ChargeApplicationService.class.getName());

	private final IRepository<Charge, ChargeID, ChargeSaveDTO, IExposedFilter> chargeRepository;
	private final IRepository<Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter> invoiceRepository;
	private final IChargeManager chargeManager;

	@Autowired
	public ChargeApplicationService(
			@NotNull @Qualifier("chargeRepository") IRepository<Charge, ChargeID, ChargeSaveDTO, IExposedFilter> chargeRepository,
			@NotNull @Qualifier("invoiceRepository") IRepository<Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter> invoiceRepository,
			IChargeManager chargeManager) {
		this.chargeRepository = chargeRepository;
		this.invoiceRepository = invoiceRepository;
		this.chargeManager = chargeManager;
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
				.create(subsciber -> 
				invoiceRepository.find(null)
					.flatMap(invoices -> Observable.from(invoices))
						.flatMap(invoice -> chargeManager
								.conductCharge(new InvoicePMDTO(invoice.getCustomer(), invoice.getPrice()), creditCard))
							.flatMap(chargeSaveDTO -> chargeRepository.create(chargeSaveDTO))
								.subscribe(
											charge -> {
												new ChargeExecutionDTO("Charge is successfully conducted", charge.getStatus());
												},
											th -> {
					LOGGER.log(Level.WARNING, String.format("Error occurred while creating charge"));
					subsciber.onError(new Exception("Failed to create charge"));
				}));

		/*
		 * 
		 * // get invoice by id 
		 * // Invoice invoice = invoiceRepository.find(f);
		 * Invoice invoice = null; InvoicePMDTO invoiceDTO = new
		 * InvoicePMDTO.Builder().build();
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
