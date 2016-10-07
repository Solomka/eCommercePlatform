package ukma.eCommerce.controller;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.async.DeferredResult;

import ukma.eCommerce.controller.vo.CreditCardVO;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.service.ChargeService;
import ukma.eCommerce.util.validation.ValidationResult;
import ukma.eCommerce.util.validation.Validators;

public class CardController {

	@Autowired
	private ChargeService paymentService;

	// somehow get credit card data
	public DeferredResult<Card> addNewCreditCard(String number, String cvv, DateTime expDate) {

		final CreditCardVO form = new CreditCardVO(number, cvv, expDate);

		// get validator for a given form
		final ValidationResult validation = Validators.forClass(CreditCardVO.class).validate(form);

		final DeferredResult<Card> defferedResult = new DeferredResult<>(5_000L);

		if (!validation.isValid()) {
			// form is invalid, show info to user
			defferedResult.setErrorResult(validation.getMessage());
			return defferedResult;
		}
		// try to add new card
		/*
		 * paymentService.addCard(form, new IRetrieveCallback<Card>() { // Card
		 * card = new Card(Card.Builder(CardForm);
		 * 
		 * @Override public void onResult(Card result) { // provide result
		 * defferedResult.setResult(result); }
		 * 
		 * @Override public void onFailure(Throwable th) { // show exception
		 * defferedResult.setErrorResult(th); } });
		 */
		return defferedResult;
	}

}
