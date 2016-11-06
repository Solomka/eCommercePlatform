package ukma.eCommerce.util.repository.filter.sql;

import ukma.eCommerce.util.repository.filter.IFilter;

/**
 * Created by Максим on 11/6/2016.
 */
public class Or implements IFilter<String> {

    private IFilter<String> first;
    private IFilter<String> second;

    public Or(IFilter<String> first, IFilter<String> second) {
        this.first = first;
        this.second = second;
    }

    public IFilter<String> getFirst() {
        return first;
    }

    public Or setFirst(IFilter<String> first) {
        this.first = first;
        return this;
    }

    public IFilter<String> getSecond() {
        return second;
    }

    public Or setSecond(IFilter<String> second) {
        this.second = second;
        return this;
    }

    @Override
    public String toFilter() {
        return String.format("( %s OR %s )", first.toFilter(), second.toFilter());
    }
}
