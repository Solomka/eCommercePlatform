package ukma.eCommerce.core.paymentModule.repository;

import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;
import ukma.eCommerce.util.filter.BasicFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

/**
 * Repository abstraction. It is a base interface for all
 * repositories.
 * @author Max
 * */
public interface IRepository<BO, Key, VO, Filter extends BasicFilter > {
	/**
	 * Asynchronously finds and returns collection of objects in abstract repository 
	 * whose type matches specified one
	 * @param filter filter chain
	 * @param callback callback to process results 
	 * */
	void find(@Null Filter filter, @NotNull IRetrieveCallback<Collection<BO>> callback);
	/**
	 * Asynchronously creates object in repository from given form. In case of successful
	 * creation callback will return newly created instance of object
	 * @param vo form to create object from
	 * @param callback callback to process results 
	 * */
	void create(@NotNull VO vo, @NotNull IRetrieveCallback<BO> callback);
	/**
	 * Asynchronously deletes object from repository by given key
	 * @param key key which uniquely identifies object
	 * @param callback callback to process results 
	 * */
	void delete(@NotNull Key key, @NotNull IUpdateCallback callback);
	/**
	 * Asynchronously updates object in repository using given key 
	 * and update form. In case of successful
	 * update callback will return updated instance of object
	 * @param key key which uniquely identifies object
	 * @param callback callback to process results 
	 * @param vo fulfilled form to update object from
	 * */
	void update(@NotNull VO vo, @NotNull Key key, @NotNull IRetrieveCallback<BO> callback);
	
}
