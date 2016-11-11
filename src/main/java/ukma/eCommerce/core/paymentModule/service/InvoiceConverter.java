package ukma.eCommerce.core.paymentModule.service;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderProxy;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceSaveDTO;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by Максим on 11/11/2016.
 */
final class InvoiceConverter {

    private InvoiceConverter() {
        throw new RuntimeException();
    }

    static InvoiceSaveDTO convert(@NotNull OrderProxy proxy) {
        Objects.requireNonNull(proxy);
        return new InvoiceSaveDTO();
    }

    public static InvoiceSaveDTO convert(InvoiceDTO invoiceDTO) {
        return null;
    }
}
