package ukma.eCommerce.util.repository.filter.sql;

import ukma.eCommerce.util.repository.IFilter;

/**
 * Created by Максим on 11/6/2016.
 */
public class And implements IFilter<String> {

    private IFilter<String> first;
    private IFilter<String> second;

    public And(IFilter<String> first, IFilter<String> second) {
        this.first = first;
        this.second = second;
    }

    public IFilter<String> getFirst() {
        return first;
    }

    public And setFirst(IFilter<String> first) {
        this.first = first;
        return this;
    }

    public IFilter<String> getSecond() {
        return second;
    }

    public And setSecond(IFilter<String> second) {
        this.second = second;
        return this;
    }

    @Override
    public String toFilter() {
        return String.format("( %s AND %s )", first.toFilter(), second.toFilter());
    }
}
