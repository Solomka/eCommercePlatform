package ukma.eCommerce.domain;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.controller.vo.CardForm;
import ukma.eCommerce.controller.vo.CardsForm;
import ukma.eCommerce.controller.vo.ChargeStatisticsForm;
import ukma.eCommerce.controller.vo.OrderForm;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.domain.bo.Charge;
import ukma.eCommerce.util.IRetrieveCallback;

// translates value objects into corresponding method calls of payment service domain model
public interface IPaymentService {

	void handleOrder(@NotNull OrderForm form, @NotNull IRetrieveCallback<Charge> callback);
	
	void fetchCharges(@NotNull ChargeStatisticsForm form, IRetrieveCallback<Collection<Charge>> callback);
	
	void fetchCards(@NotNull CardsForm form, @NotNull IRetrieveCallback<Collection<Card>> callback); 
	
	void addCard(@NotNull CardForm form, @NotNull IRetrieveCallback<Card> callback);
	
}
