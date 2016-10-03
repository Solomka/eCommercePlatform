package ukma.eCommerce.service;

import ukma.eCommerce.controller.vo.CreditCard;
import ukma.eCommerce.domain.bo.Invoice;

public interface ChargeService {

	public void createCharge(CreditCard creditCard, Invoice incoice);

}
