package ukma.eCommerce.service;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.controller.vo.CardsForm;
import ukma.eCommerce.controller.vo.ChargeStatisticsForm;
import ukma.eCommerce.controller.vo.ChargeVO;
import ukma.eCommerce.controller.vo.CreditCardVO;
import ukma.eCommerce.controller.vo.OrderVO;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.domain.bo.Payment;
import ukma.eCommerce.util.IRetrieveCallback;

//translates value objects into corresponding method calls of payment service domain model
public interface ChargeService {

	void handleOrder(@NotNull OrderVO form, @NotNull IRetrieveCallback<Payment> callback);

	void fetchCharges(@NotNull ChargeStatisticsForm form, IRetrieveCallback<Collection<Payment>> callback);

	//void fetchCards(@NotNull CardsForm form, @NotNull IRetrieveCallback<Collection<Card>> callback);

	//void addCard(@NotNull CreditCardVO form, @NotNull IRetrieveCallback<Card> callback);
	
	/*
	 * 
	 * 
	 */

	public void createCharge(@NotNull CreditCardVO creditCard, @NotNull ChargeVO chargeVO,
			@NotNull IRetrieveCallback<Payment> callback);

}
