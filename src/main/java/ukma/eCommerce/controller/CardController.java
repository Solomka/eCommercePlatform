package ukma.eCommerce.controller;

import org.joda.time.DateTime;
import org.springframework.web.context.request.async.DeferredResult;

import ukma.eCommerce.controller.vo.CreditCard;
import ukma.eCommerce.domain.IPaymentService;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.validation.ValidationResult;
import ukma.eCommerce.util.validation.Validators;

public class CardController {
	// inject somehow
	private IPaymentService paymentService;
	
	// somehow get credit card data
	public DeferredResult<Card> addNewCreditCard(String number, String cvv, DateTime expDate) {
		
		final CreditCard form = new CreditCard(number, cvv, expDate);
		// get validator for a given form
		final ValidationResult validation = Validators.forClass(CreditCard.class).validate(form);
		
		final DeferredResult<Card> defferedResult = new DeferredResult<>(5_000L);
		
		if(!validation.isValid()) {
			// form is invalid, show info to user
			defferedResult.setErrorResult(validation.getMessage());
			return defferedResult;
		}
		// try to add new card
		paymentService.addCard(form, new IRetrieveCallback<Card>() {
			
			@Override
			public void onResult(Card result) {
				// provide result
				defferedResult.setResult(result);
			}
			
			@Override
			public void onFailure(Throwable th) {
				// show exception
				defferedResult.setErrorResult(th);
			}
		});
		
		return defferedResult;
	}

}
