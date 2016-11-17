package ukma.eCommerce.core.paymentModule.service;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
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

    static InvoiceSaveDTO convert(@NotNull Order order) {
        Objects.requireNonNull(order);
        return new InvoiceSaveDTO();
    }

    public static InvoiceSaveDTO convert(InvoiceDTO invoiceDTO) {
        return null;
    }
}
