package ukma.eCommerce.util.repository.filter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * This is base filter interface for repository filter
 * </p>
 * Created by Максим on 11/6/2016.
 *
 * @param <F> filter type
 */

public interface IFilter<F> {

    /**
     * Converts filter into repository-specific filter
     *
     * @return filter representation
     */
    @NotNull
    F toFilter();

}
