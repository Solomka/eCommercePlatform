package ukma.eCommerce.paymentModule;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.controller.vo.ChargeVO;
import ukma.eCommerce.controller.vo.CreditCardVO;
import ukma.eCommerce.controller.vo.OrderVO;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.domain.bo.Payment;
import ukma.eCommerce.repository.filter.CardFilter;
import ukma.eCommerce.repository.filter.PaymentFilter;
import ukma.eCommerce.util.IRetrieveCallback;

public class ChargeManagerImpl implements ChargeManager {

	public void conductCharge(Payment charge) {

	}

	@Override
	public void processOrder(OrderVO form, IRetrieveCallback<Payment> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fetchCharges(String userId, PaymentFilter filter, IRetrieveCallback<Collection<Payment>> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fetchCards(String userId, CardFilter filter, IRetrieveCallback<Card> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void conductCharge(@NotNull CreditCardVO creditCardVO, @NotNull ChargeVO chargeVO,
			@NotNull IRetrieveCallback<ChargeVO> callback) {

	}

}
