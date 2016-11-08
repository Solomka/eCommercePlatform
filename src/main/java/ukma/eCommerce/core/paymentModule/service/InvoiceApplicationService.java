package ukma.eCommerce.core.paymentModule.service;

import org.springframework.stereotype.Service;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/6/2016.
 */
@Service
final class InvoiceApplicationService implements IInvoiceApplicationService {
    @Override
    public Observable<Invoice> createInvoice(@NotNull InvoiceDTO invoiceDTO) {
        return null;
    }
}
