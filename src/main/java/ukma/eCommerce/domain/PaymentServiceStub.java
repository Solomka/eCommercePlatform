package ukma.eCommerce.domain;

import java.util.Collection;

import ukma.eCommerce.controller.vo.CardForm;
import ukma.eCommerce.controller.vo.CardsForm;
import ukma.eCommerce.controller.vo.ChargeStatisticsForm;
import ukma.eCommerce.controller.vo.OrderForm;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.domain.bo.Charge;
import ukma.eCommerce.repository.IRepository;
import ukma.eCommerce.repository.filter.CardFilter;
import ukma.eCommerce.repository.filter.Ordering;
import ukma.eCommerce.util.IRetrieveCallback;

public class PaymentServiceStub implements IPaymentService {
	
	private IRepository<Card, String, CardForm, CardFilter> repository;
	
	private IPaymentDomainModel model;

	@Override
	public void handleOrder(OrderForm form, IRetrieveCallback<Charge> callback) {
		model.processOrder(form, callback);
	}

	@Override
	public void fetchCharges(ChargeStatisticsForm form, IRetrieveCallback<Collection<Charge>> callback) {
		
	}

	@Override
	public void fetchCards(CardsForm form, IRetrieveCallback<Collection<Card>> callback) {
		repository.find(fromForm(form), callback);
	}

	@Override
	public void addCard(CardForm form, IRetrieveCallback<Card> callback) {
		repository.create(form, callback);
	}
	
	private CardFilter fromForm(CardsForm form) {
		
		CardFilter filter = new CardFilter();
		
		filter.setMinMoneyAmount(form.getMinMoneyAmount())
		.setMaxMoneyAmount(form.getMaxMoneyAmount())
		.setOrdering(Ordering.ASCENDING)
		.setMaxItems(100);
		
		return filter;
	}

}
