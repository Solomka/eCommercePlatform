package ukma.eCommerce.domain;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import ukma.eCommerce.controller.vo.OrderForm;
import ukma.eCommerce.domain.bo.Card;
import ukma.eCommerce.domain.bo.Charge;
import ukma.eCommerce.repository.filter.CardFilter;
import ukma.eCommerce.repository.filter.ChargeFilter;
import ukma.eCommerce.util.IRetrieveCallback;

/**
 * Core payment business logic contract
 * @author Max
 * */
public interface IPaymentDomainModel {
	/**
	 * Creates and processes user's order asynchronously. Result can be handled via
	 * supplied callback
	 * @param form order form to process
	 * @param callback callback to process result
	 * */
	void processOrder(@NotNull OrderForm form, @NotNull IRetrieveCallback<Charge> callback);
	/**
	 * Fetches user specific charge statistic asynchronously. Result can be handled via
	 * supplied callback
	 * @param userId unique user id
	 * @param callback callback to process result
	 * @param filter filter to apply, may be null
	 * */
	// do we really should use string as user id?
	void fetchCharges(@NotNull String userId, @Null ChargeFilter filter, IRetrieveCallback<Collection<Charge>> callback);
	/**
	 * Fetches set of user credit cards asynchronously. Result can be handled via
	 * supplied callback
     * @param userId unique user id
	 * @param callback callback to process result
	 * @param filter filter to apply, may be null
	 * */
	// do we really should use string as user id?
	void fetchCards(@NotNull String userId, @Null CardFilter filter, @NotNull IRetrieveCallback<Card> callback);
	
	// add whatever you need
	//......
	
}
