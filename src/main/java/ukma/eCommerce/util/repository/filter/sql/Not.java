package ukma.eCommerce.util.repository.filter.sql;

import ukma.eCommerce.util.repository.IFilter;

/**
 * Created by Максим on 11/6/2016.
 */
public class Not implements IFilter<String> {

    private IFilter<String> value;

    public Not(IFilter<String> value) {
        this.value = value;
    }

    public IFilter<String> getValue() {
        return value;
    }

    public Not setValue(IFilter<String> value) {
        this.value = value;
        return this;
    }

    @Override
    public String toFilter() {
        return String.format("(NOT %s)", value.toFilter());
    }
}
