package ukma.eCommerce.util;

import rx.Observable;
import ukma.eCommerce.util.filter.BasicFilter;

import javax.validation.constraints.NotNull;

/**
 * Repository abstraction. It is a base interface for all repositories.
 *
 * @author Max
 */
public interface IRepository<BO, Key, VO, Filter extends BasicFilter> extends IReadonlyRepository<BO, Filter> {

    /**
     * Asynchronously creates object in repository from given form. In case of
     * successful creation callback will return newly created instance of object
     *
     * @param vo form to create object from
     * @return instance of {@linkplain Observable} to monitor request status
     */
    Observable<BO> create(@NotNull VO vo);

    /**
     * Asynchronously deletes object from repository by given key
     *
     * @param key key which uniquely identifies object
     * @return instance of {@linkplain Observable} to monitor request status
     */
    Observable<Void> delete(@NotNull Key key);

    /**
     * Asynchronously updates object in repository using given key and update
     * form. In case of successful update callback will return updated instance
     * of object
     *
     * @param bo object instance to update
     * @return instance of {@linkplain Observable} to monitor request status
     */
    Observable<BO> update(@NotNull BO bo);

}
