package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.InvoicePO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
@Repository("invoiceRepository")
@Transactional
public class InvoiceRepository
		extends AHibernateRepository<Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter, InvoicePO, UUID> {

	@SuppressWarnings("unchecked")
	@Override

	public Collection<Invoice> find(IExposedFilter f) {
		return findAllBySpecification((Specification<InvoicePO>) f.toFilter()).stream()
				.map(invoicePO -> InvoicePOConverter.toInvoice(invoicePO)).collect(Collectors.toList());
	}

	@Override
	public Invoice create(InvoiceSaveDTO invoiceSaveDTO) {
		final InvoicePO invoicePO = InvoicePOConverter.fromInvoiceSaveDTO(invoiceSaveDTO);
		savePO(invoicePO);
		return InvoicePOConverter.toInvoice(invoicePO);
	}

	@Override
	public Invoice update(Invoice invoice) {
		final InvoicePO invoicePO = updatePO(InvoicePOConverter.fromInvoice(invoice));
		return InvoicePOConverter.toInvoice(invoicePO);
	}

	@Override
	public void delete(InvoiceID invoiceId) {
		deletePOById(invoiceId.getId());
	}

}
