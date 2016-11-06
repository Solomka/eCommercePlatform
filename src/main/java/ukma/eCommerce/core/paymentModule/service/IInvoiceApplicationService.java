package ukma.eCommerce.core.paymentModule.service;


import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;

import javax.validation.constraints.NotNull;

/**
 * Handles operations under invoices
 */
public interface IInvoiceApplicationService {

    /**
     * Creates invoice instance from specified invoice dto
     *
     * @param invoiceDTO dto to process
     * @return rx Observable to monitor process
     */
    @NotNull
    Observable<Invoice> createInvoice(@NotNull InvoiceDTO invoiceDTO);

}
