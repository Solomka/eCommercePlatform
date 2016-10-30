package ukma.eCommerce.core.paymentModule.domainLogic.repository;

import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.filter.BasicFilter;

/**
 * Created by Максим on 10/31/2016.
 */
public class StripeFilter extends BasicFilter {

    private CustomerID customerID;

    public CustomerID getCustomerID() {
        return customerID;
    }

    public StripeFilter setCustomerID(CustomerID customerID) {
        this.customerID = customerID;
        return this;
    }
}
