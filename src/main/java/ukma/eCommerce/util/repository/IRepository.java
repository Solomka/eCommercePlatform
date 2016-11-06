package ukma.eCommerce.util.repository;

import rx.Observable;
import ukma.eCommerce.util.repository.filter.IFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

/**
 * Repository abstraction. It is a base interface for all repositories.
 *
 * @param <T>  object type to return as result
 * @param <K>  key type
 * @param <E>  object to persist
 * @param <F>  filter to use
 * @param <Ft> filter type to use
 * @author Max
 */
public interface IRepository<T, K, E, Ft, F extends IFilter<Ft>> {

    /**
     * Asynchronously finds and returns collection of objects in abstract
     * repository whose type matches specified one
     *
     * @param f filter chain
     * @return instance of {@linkplain Observable} to monitor request status
     */
    Observable<Collection<T>> find(@Null F f);

    /**
     * Asynchronously creates object in repository from given form. In case of
     * successful creation callback will return newly created instance of object
     *
     * @param e form to create object from
     * @return instance of {@linkplain Observable} to monitor request status
     */
    Observable<T> create(@NotNull E e);

    /**
     * Asynchronously deletes object from repository by given key
     *
     * @param k key which uniquely identifies object
     * @return instance of {@linkplain Observable} to monitor request status
     */
    Observable<Void> delete(@NotNull K k);

    /**
     * Asynchronously updates object in repository using given key and update
     * form. In case of successful update callback will return updated instance
     * of object
     *
     * @param t object instance to update
     * @return instance of {@linkplain Observable} to monitor request status
     */
    Observable<T> update(@NotNull T t);

}
