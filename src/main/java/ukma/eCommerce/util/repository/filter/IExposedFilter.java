package ukma.eCommerce.util.repository.filter;

import ukma.eCommerce.util.repository.IFilter;

/**
 * <p>
 *     Filter to be exposed to repository clients; this
 *     interface just keeps user away from any realization
 *     details which can change if we change parameter type
 *     of {@linkplain IFilter}
 * </p>
 * Created by Максим on 11/6/2016.
 */
public interface IExposedFilter extends IFilter<Object> {// specify parameter type
}
