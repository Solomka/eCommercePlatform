package ukma.eCommerce.core.paymentModule.repository;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.InvoicePO;

/**
 * 
 * @author Solomka
 *
 */
final class InvoicePOConverter {

	private InvoicePOConverter() {
		throw new RuntimeException();
	}

	/**
	 * convert from InvoiceSaveDTO to InvoicePO
	 * 
	 * @param sellerSaveDTO
	 * @return
	 */

	@NotNull
	static InvoicePO fromInvoiceSaveDTO(@NotNull InvoiceSaveDTO customerSaveDTO) {

		return new InvoicePO();
	}

	/**
	 * convert from Invoice to InvoicePO
	 * 
	 * @param invoice
	 * @return
	 */
	@NotNull
	static InvoicePO fromInvoice(@NotNull Invoice invoice) {
		return new InvoicePO();
	}

	/**
	 * convert from InvoicePO to Invoice
	 * 
	 * @param InvoicePO
	 * @return
	 */

	@NotNull
	static Invoice toInvoice(@NotNull InvoicePO invoicePO) {
		return new Invoice.Builder().build();
	}

}
