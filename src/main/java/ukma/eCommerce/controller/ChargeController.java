package ukma.eCommerce.controller;

import java.util.logging.Logger;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import ukma.eCommerce.controller.vo.ChargeVO;
import ukma.eCommerce.controller.vo.CreditCardVO;
import ukma.eCommerce.controller.vo.OrderVO;
import ukma.eCommerce.domain.bo.Customer;
import ukma.eCommerce.domain.bo.Invoice;
import ukma.eCommerce.domain.bo.Order;
import ukma.eCommerce.domain.bo.Payment;
import ukma.eCommerce.service.ChargeService;
import ukma.eCommerce.service.InvoiceService;
import ukma.eCommerce.service.OrderService;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.validation.ValidationResult;
import ukma.eCommerce.util.validation.Validators;

@Controller
public class ChargeController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ChargeService chargeService;

	private final Logger logger = (Logger) LoggerFactory.make();

	/**
	 * Receive all data related to the order: userInfo cardInfo !!! orderInfo -
	 * in some form ???
	 * create PaymentBO only if successfull Stripe paying
	 * after that - add  Order + Invice + Payment to DB
	 * 
	 * 
	 * @param cust
	 * @param number
	 * @param cvc
	 * @param expDate
	 * @param order
	 * @return
	 */

	@RequestMapping(value = "/createCharge", method = RequestMethod.POST)
	public void createCharge(@AuthenticationPrincipal Customer cust, String number, String cvc, DateTime expDate,
			OrderVO order) {

		/*
		 * create creditCardVO check what to do if not valid cardInfo passed- ?
		 */
		CreditCardVO creditCardVO = createCreditCardVO(number, cvc, expDate);

		/*
		 * create OrderVO check what to do if not valid orderInfo passed - ?
		 */
		OrderVO orderVO = createOrderVO(order);

		/*
		 * create InvoiceVO if orderVo created successfully
		 */
		Invoice invoiceVO = createInvoice(orderVO);

		/*
		 * create ChargeVO if invoiceVO created successfully
		 */
		ChargeVO chargeVO = createChargeVO(invoiceVO);

		/*
		 * call service methods passing them validated VO =)
		 */
		final DeferredResult<Payment> defferedResult = new DeferredResult<>(5_000L);

		chargeService.createCharge(creditCardVO, chargeVO, new IRetrieveCallback<Payment>() {
			@Override
			public void onResult(Payment result) {
				// provide result
				defferedResult.setResult(result);
			}

			@Override
			public void onFailure(Throwable th) {
				// show exception
				defferedResult.setErrorResult(th);
			}

		});

		if (defferedResult.hasResult() && defferedResult.getResult().getClass().equals(Payment.class)) {
			final DeferredResult<Order> defferedOrderResult = new DeferredResult<>(5_000L);
			if (defferedOrderResult.hasResult() && defferedOrderResult.getResult().getClass().equals(Order.class)) {
				final DeferredResult<Invoice> defferedInvoiceResult = new DeferredResult<>(5_000L);
				{
					if (defferedInvoiceResult.hasResult()
							&& defferedInvoiceResult.getResult().getClass().equals(Invoice.class)) {
						logger.info("Success!!!");
					}
				}
			}
		}
	}

	/**
	 * Create CreditCardVO
	 * 
	 * validate or not validate - (it seems like Stripe execute validation on
	 * its own - ??? )
	 */
	private CreditCardVO createCreditCardVO(String number, String cvc, DateTime expDate) {
		final CreditCardVO creditCard = new CreditCardVO(number, cvc, expDate);
		final ValidationResult validation = Validators.forClass(CreditCardVO.class).validate(creditCard);
		if (!validation.isValid()) {
			/*
			 * creditCard = new CreditCard(); return creaditCard;
			 * 
			 * return validation;
			 */
		}

		return creditCard;
	}

	/**
	 * Create OrderVO
	 * 
	 */
	private OrderVO createOrderVO(OrderVO order) {

		return null;
	}

	/**
	 * Create OrderBO
	 */

	private DeferredResult<Order> createOrderBO(OrderVO orderVO) {
		final DeferredResult<Order> defferedOrderResult = new DeferredResult<>(5_000L);

		orderService.createOrder(orderVO, new IRetrieveCallback<Order>() { //
			// Card card = new Card(Card.Builder(CardForm);

			@Override
			public void onResult(Order result) { // provide result
				defferedOrderResult.setResult(result);
			}

			@Override
			public void onFailure(Throwable th) { // show exception
				defferedOrderResult.setErrorResult(th);
			}
		});
		return defferedOrderResult;
	}

	/**
	 * Create InvoiceVO
	 * 
	 */
	private Invoice createInvoice(OrderVO orderVO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Create ChargeVO
	 * 
	 */
	private ChargeVO createChargeVO(Invoice invoiceVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
