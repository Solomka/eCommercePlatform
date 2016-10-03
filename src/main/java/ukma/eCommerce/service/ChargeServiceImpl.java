package ukma.eCommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ukma.eCommerce.controller.vo.CreditCard;
import ukma.eCommerce.domain.bo.Charge;
import ukma.eCommerce.domain.bo.Invoice;
import ukma.eCommerce.paymentModule.ChargeManager;

@Service("chargeService")
public class ChargeServiceImpl implements ChargeService {

	@Autowired
	ChargeManager chargeManager;

	public void createCharge(CreditCard creditCard, Invoice invoice) {

		Charge charge = new Charge();

		chargeManager.conductCharge(charge);

	}

}
