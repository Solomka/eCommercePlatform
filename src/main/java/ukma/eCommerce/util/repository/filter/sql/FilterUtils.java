package ukma.eCommerce.util.repository.filter.sql;

import ukma.eCommerce.util.repository.filter.IFilter;

/**
 * Created by Максим on 11/6/2016.
 */
public final class FilterUtils {

    private FilterUtils() {
        throw new RuntimeException();
    }

    public static And and(IFilter<String> first, IFilter<String> second) {
        return new And(first, second);
    }

    public static Or or(IFilter<String> first, IFilter<String> second) {
        return new Or(first, second);
    }

}
