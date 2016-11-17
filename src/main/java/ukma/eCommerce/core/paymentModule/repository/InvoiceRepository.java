package ukma.eCommerce.core.paymentModule.repository;

import org.springframework.stereotype.Repository;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.InvoicePO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import java.util.Collection;
import java.util.UUID;

/**
 * 
 * @author Solomka
 *
 */
@Repository("invoiceRepository")
public class InvoiceRepository extends AHibernateRepository<InvoicePO, UUID> implements
		IRepository<Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter> {

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
