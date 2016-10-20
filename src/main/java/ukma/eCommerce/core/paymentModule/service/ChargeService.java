package ukma.eCommerce.core.paymentModule.service;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ukma.eCommerce.core.paymentModule.domainLogic.IChargeManager;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.CreditCardDTO;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.filter.CardFilter;

@Service("chargeService")
public class ChargeService implements IChargeService {
	private IRepository<CreditCard, String, CreditCard, CardFilter> repository;

	@Autowired
	IChargeManager IChargeManager;

	@Override
	public void fetchCharges(Period form, IRetrieveCallback<Collection<Charge>> callback) {

	}

	@Override
	public void createCharge(@NotNull CreditCardDTO creditCard, @NotNull ChargeDTO chargeDTO,
			@NotNull IRetrieveCallback<Charge> callback) {

	}

}
