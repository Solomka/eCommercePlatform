package ukma.eCommerce.core.presentationModule.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import ukma.eCommerce.core.paymentModule.domainLogic.IChargeManager;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.core.paymentModule.service.IChargeApplicationService;
import ukma.eCommerce.core.paymentModule.service.IInvoiceApplicationService;
import ukma.eCommerce.core.paymentModule.service.IOrderApplicationService;
import ukma.eCommerce.core.userModule.model.domain.bo.Customer;

@Controller
public class ChargeController {

    @Autowired
    private IOrderApplicationService IOrderService;

    @Autowired
    private IInvoiceApplicationService IInvoiceService;

    @Autowired
    private IChargeApplicationService chargeService;
    // for test purposes only
    @Autowired
    private IChargeManager manager;

//	private final Logger logger = (Logger) LoggerFactory.make();

    @RequestMapping(value = "/charge", method = RequestMethod.GET)
    public void createCharge() {
      //  ((ChargeManager)manager).f();
    }

    /**
     * Receive all data related to the order: userInfo cardInfo !!! orderInfo -
     * in some form ???
     * create PaymentBO only if successfull Stripe paying
     * after that - add  Order + Invice + Payment to DB
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
                             Order order) {

		/*
         * create creditCardVO check what to do if not valid cardInfo passed- ?
		 */
        CreditCardDTO creditCardDTO = createCreditCardDTO(number, cvc, expDate);

		/*
         * create Order check what to do if not valid orderInfo passed - ?
		 */
        OrderDTO orderDTO = createOrderDTO(order);

		/*
         * create Invoice if orderVo created successfully
		 */
        InvoiceDTO invoiceDVO = createInvoiceDTO(orderDTO);


		/*
         * create Charge if invoiceVO created successfully
		 */
        ChargeDTO chargeDTO = createChargeDTO(invoiceDVO);

		/*
		 * call service methods passing them validated VO =)
		 */
        final DeferredResult<Charge> defferedResult = new DeferredResult<>(5_000L);

        /*chargeService.createCharge(creditCardDTO, chargeDTO, new IRetrieveCallback<Charge>() {
            @Override
            public void onResult(Charge result) {
                // provide result
                defferedResult.setResult(result);
            }

            @Override
            public void onFailure(Throwable th) {
                // show exception
                defferedResult.setErrorResult(th);
            }

        });*/

        if (defferedResult.hasResult() && defferedResult.getResult().getClass().equals(Charge.class)) {
            final DeferredResult<ukma.eCommerce.core.paymentModule.model.domain.bo.Order> defferedOrderResult = new DeferredResult<>(5_000L);
            if (defferedOrderResult.hasResult() && defferedOrderResult.getResult().getClass().equals(ukma.eCommerce.core.paymentModule.model.domain.bo.Order.class)) {
                final DeferredResult<Invoice> defferedInvoiceResult = new DeferredResult<>(5_000L);
                {
                    if (defferedInvoiceResult.hasResult()
                            && defferedInvoiceResult.getResult().getClass().equals(Invoice.class)) {
                        //logger.info("Success!!!");
                    }
                }
            }
        }
    }

    /**
     * Create CreditCard
     * <p>
     * validate or not validate - (it seems like Stripe execute validation on
     * its own - ??? )
     */
    private CreditCardDTO createCreditCardDTO(String number, String cvc, DateTime expDate) {
        final CreditCardDTO creditCard = new CreditCardDTO(number, cvc, expDate);
        /*final ValidationResult validation = Validators.forClass(CreditCardDTO.class).validate(creditCard);
        if (!validation.isValid()) {

        }*/

        return creditCard;
    }

    /**
     * Create Order
     */
    private OrderDTO createOrderDTO(Order order) {

        return null;
    }

    /**
     * Create OrderBO
     */

    /*private DeferredResult<ukma.eCommerce.core.paymentModule.model.domain.bo.Order> createOrderBO(OrderDTO orderDTO) {
        final DeferredResult<ukma.eCommerce.core.paymentModule.model.domain.bo.Order> defferedOrderResult = new DeferredResult<>(5_000L);

        IOrderApplicationService.createOrder(orderDTO, new IRetrieveCallback<ukma.eCommerce.core.paymentModule.model.domain.bo.Order>() { //
            // Card card = new Card(Card.Builder(CardForm);

            @Override
            public void onResult(ukma.eCommerce.core.paymentModule.model.domain.bo.Order result) { // provide result
                defferedOrderResult.setResult(result);
            }

            @Override
            public void onFailure(Throwable th) { // show exception
                defferedOrderResult.setErrorResult(th);
            }
        });
        return defferedOrderResult;
    }*/

    /**
     * Create Invoice
     */
    private InvoiceDTO createInvoiceDTO(OrderDTO orderVO) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Create Charge
     */
    private ChargeDTO createChargeDTO(InvoiceDTO invoiceVO) {
        // TODO Auto-generated method stub
        return null;
    }


}
