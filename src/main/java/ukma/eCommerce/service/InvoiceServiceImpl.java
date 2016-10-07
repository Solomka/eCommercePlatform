package ukma.eCommerce.service;

import org.springframework.stereotype.Service;

import ukma.eCommerce.domain.bo.Invoice;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {

	public Invoice createInvoice() {

		return new Invoice();
	}

}
