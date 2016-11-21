package ukma.eCommerce.util.repository;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import rx.Observable;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * Repository abstraction. It is a base interface for all repositories.
 *
 * @param <T>
 *            object type to return as result
 * @param <K>
 *            key type
 * @param <E>
 *            object to persist
 * @param <F>
 *            filter to use
 * @author Max
 */
public interface IRepository<T, K, E, F extends IExposedFilter> {

	/**
	 * Asynchronously finds and returns collection of objects in abstract
	 * repository whose type matches specified one
	 *
	 * @param f
	 *            filter chain, can be null
	 * @return instance of {@linkplain Observable} to monitor request status
	 */
	@NotNull
	Observable<Collection<T>> find(F f);

	/**
	 * Asynchronously creates object in repository from given form. In case of
	 * successful creation callback will return newly created instance of object
	 *
	 * @param e
	 *            form to create object from
	 * @return instance of {@linkplain Observable} to monitor request status
	 */
	@NotNull
	Observable<T> create(@NotNull E e);

	/**
	 * Asynchronously deletes object from repository by given key
	 *
	 * @param k
	 *            key which uniquely identifies object
	 * @return instance of {@linkplain Observable} to monitor request status
	 */
	@NotNull
	Observable<Boolean> delete(@NotNull K k);

	/**
	 * Asynchronously updates object in repository using given key and update
	 * form. In case of successful update callback will return updated instance
	 * of object
	 *
	 * @param t
	 *            object instance to update
	 * @return instance of {@linkplain Observable} to monitor request status
	 */
	@NotNull
	Observable<T> update(@NotNull T t);
}
