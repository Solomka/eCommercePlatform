package ukma.eCommerce.core.userModule.repository.filter;

import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.util.repository.filter.BasicFilter;

/**
 * Created by Максим on 10/31/2016.
 */
public class CustomerFilter extends BasicFilter {

    private InvoiceID invoiceID;

    public InvoiceID getInvoiceID() {
        return invoiceID;
    }

    public CustomerFilter withInvoiceID(InvoiceID invoiceID) {
        this.invoiceID = invoiceID;
        return this;
    }
}
