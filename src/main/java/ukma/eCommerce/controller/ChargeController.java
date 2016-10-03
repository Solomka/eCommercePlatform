package ukma.eCommerce.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ukma.eCommerce.controller.vo.CreditCard;
import ukma.eCommerce.domain.bo.Customer;
import ukma.eCommerce.domain.bo.Invoice;
import ukma.eCommerce.domain.bo.Order;
import ukma.eCommerce.service.ChargeService;
import ukma.eCommerce.service.InvoiceService;
import ukma.eCommerce.service.OrderService;

@Controller
public class ChargeController {

	@Autowired
	private ChargeService chargeService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private InvoiceService invoiceService;

	@RequestMapping(value = "/createCharge", method = RequestMethod.POST)
	public void createCharge(@AuthenticationPrincipal Customer cust, String number, String cvc, DateTime expDate) {
		CreditCard creditCard = new CreditCard(number, cvc, expDate);
		Order order = orderService.createOrder();
		Invoice invoice = invoiceService.createInvoice();
		chargeService.createCharge(creditCard, invoice);

	}

}
