package ukma.eCommerce.core.paymentModule.repository;

import org.joda.time.DateTime;
import ukma.eCommerce.util.repository.filter.IFilter;

/**
 * Created by Максим on 11/6/2016.
 */
public class ChargeBefore implements IFilter<String> {

    private DateTime beforeDate;

    public ChargeBefore(DateTime beforeDate) {
        this.beforeDate = beforeDate;
    }

    @Override
    public String toFilter() {
        return String.format("creation_date <= %s", beforeDate.toString("dd.MM.YYYY"));
    }
}
