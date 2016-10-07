package ukma.eCommerce.paymentModule;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import ukma.eCommerce.controller.vo.ChargeVO;
import ukma.eCommerce.controller.vo.CreditCardVO;
import ukma.eCommerce.controller.vo.OrderVO;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.domain.bo.Payment;
import ukma.eCommerce.repository.filter.CardFilter;
import ukma.eCommerce.repository.filter.PaymentFilter;
import ukma.eCommerce.util.IRetrieveCallback;

/**
 * Core payment business logic contract
 * 
 * @author Max
 */
public interface ChargeManager {

	/**
	 * Creates and processes user's order asynchronously. Result can be handled
	 * via supplied callback
	 * 
	 * @param form
	 *            order form to process
	 * @param callback
	 *            callback to process result
	 */
	void processOrder(@NotNull OrderVO form, @NotNull IRetrieveCallback<Payment> callback);

	/**
	 * Fetches user specific charge statistic asynchronously. Result can be
	 * handled via supplied callback
	 * 
	 * @param userId
	 *            unique user id
	 * @param callback
	 *            callback to process result
	 * @param filter
	 *            filter to apply, may be null
	 */
	// do we really should use string as user id?
	void fetchCharges(@NotNull String userId, @Null PaymentFilter filter,
			IRetrieveCallback<Collection<Payment>> callback);

	/**
	 * Fetches set of user credit cards asynchronously. Result can be handled
	 * via supplied callback
	 * 
	 * @param userId
	 *            unique user id
	 * @param callback
	 *            callback to process result
	 * @param filter
	 *            filter to apply, may be null
	 */
	// do we really should use string as user id?
	void fetchCards(@NotNull String userId, @Null CardFilter filter, @NotNull IRetrieveCallback<Card> callback);

	// add whatever you need
	// ......

	public void conductCharge(@NotNull CreditCardVO creditCardVO, @NotNull ChargeVO chargeVO,
			@NotNull IRetrieveCallback<ChargeVO> callback);

}
