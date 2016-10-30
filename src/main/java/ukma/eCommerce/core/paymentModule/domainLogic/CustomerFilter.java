package ukma.eCommerce.core.paymentModule.domainLogic;

import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.util.filter.BasicFilter;

/**
 * Created by Максим on 10/31/2016.
 */
public class CustomerFilter extends BasicFilter {

    private InvoiceID id;

    public InvoiceID getId() {
        return id;
    }

    public CustomerFilter withInvoiceId(InvoiceID id) {
        this.id = id;
        return this;
    }
}
