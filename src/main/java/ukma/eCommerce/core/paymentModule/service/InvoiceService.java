package ukma.eCommerce.core.paymentModule.service;

import org.springframework.stereotype.Service;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;
import ukma.eCommerce.util.IRetrieveCallback;

import javax.validation.constraints.NotNull;

@Service("invoiceService")
public class InvoiceService implements IInvoiceService {


    @Override
    public void createInvoice(@NotNull InvoiceDTO invoiceDTO, @NotNull IRetrieveCallback<Invoice> callback) {

    }
}
