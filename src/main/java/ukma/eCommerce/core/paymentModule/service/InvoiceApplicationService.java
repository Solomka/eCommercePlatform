package ukma.eCommerce.core.paymentModule.service;

import java.util.logging.Logger;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;

/**
 * Created by Максим on 11/6/2016.
 */
@Service
final class InvoiceApplicationService implements IInvoiceApplicationService {

	private static final Logger LOGGER = Logger.getLogger(InvoiceApplicationService.class.getName());
	
	

	@Override
	public Observable<Invoice> createInvoice(@NotNull InvoiceDTO invoiceDTO) {
		return null;
	}
}
