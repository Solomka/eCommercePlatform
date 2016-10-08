package ukma.eCommerce.core.paymentModule.service;


import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;
import ukma.eCommerce.util.IRetrieveCallback;

import javax.validation.constraints.NotNull;

public interface IInvoiceService {
    void createInvoice(@NotNull InvoiceDTO invoiceDTO, @NotNull IRetrieveCallback<Invoice> callback);
}
