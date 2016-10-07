package ukma.eCommerce.service;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

import ukma.eCommerce.controller.vo.CardsForm;
import ukma.eCommerce.controller.vo.ChargeStatisticsForm;
import ukma.eCommerce.controller.vo.ChargeVO;
import ukma.eCommerce.controller.vo.CreditCardVO;
import ukma.eCommerce.controller.vo.OrderVO;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.domain.bo.Payment;
import ukma.eCommerce.paymentModule.ChargeManager;
import ukma.eCommerce.repository.IRepository;
import ukma.eCommerce.repository.filter.CardFilter;
import ukma.eCommerce.repository.filter.Ordering;
import ukma.eCommerce.util.IRetrieveCallback;

@Service("chargeService")
public class ChargeServiceImpl implements ChargeService {
	private IRepository<Card, String, CreditCardVO, CardFilter> repository;

	@Autowired
	ChargeManager chargeManager;

	@Override
	public void handleOrder(OrderVO form, IRetrieveCallback<Payment> callback) {
		chargeManager.processOrder(form, callback);
	}

	@Override
	public void fetchCharges(ChargeStatisticsForm form, IRetrieveCallback<Collection<Payment>> callback) {

	}
/*
	@Override
	public void fetchCards(CardsForm form, IRetrieveCallback<Collection<Card>> callback) {
		repository.find(fromForm(form), callback);
	}

	@Override
	public void addCard(CreditCardVO form, IRetrieveCallback<Card> callback) {
		repository.create(form, callback);
	}
*/
	private CardFilter fromForm(CardsForm form) {

		CardFilter filter = new CardFilter();

		filter.setMinMoneyAmount(form.getMinMoneyAmount()).setMaxMoneyAmount(form.getMaxMoneyAmount())
				.setOrdering(Ordering.ASCENDING).setMaxItems(100);

		return filter;
	}
/*
	public void createCharge(CreditCardVO creditCard, Invoice invoice) {

		Payment charge = new Payment();

		chargeManager.conductCharge(charge);

	}
*/	
	public void createCharge(@NotNull CreditCardVO creditCardVO, @NotNull ChargeVO chargeVO,
			@NotNull IRetrieveCallback<Payment> callback){
		//do some chargeVO modification maybe
		final DeferredResult<ChargeVO> defferedResult = new DeferredResult<>(5_000L);
		chargeManager.conductCharge(creditCardVO, chargeVO, new IRetrieveCallback<ChargeVO>() {
			@Override
			public void onResult(ChargeVO result) {
				// provide result
				defferedResult.setResult(result);
			}

			@Override
			public void onFailure(Throwable th) {
				// show exception
				defferedResult.setErrorResult(th);
			}

		});
		
	}

}
