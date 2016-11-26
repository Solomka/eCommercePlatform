package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

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

	@Override

	public Collection<Invoice> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Invoice create(InvoiceSaveDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public void delete(InvoiceID k) {
		// TODO Auto-generated method stub
	}

	@Override
	public Invoice update(Invoice t) {
		// TODO Auto-generated method stub
		return null;
	}

}
