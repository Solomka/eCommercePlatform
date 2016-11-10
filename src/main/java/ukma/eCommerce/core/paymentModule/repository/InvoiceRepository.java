package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.InvoicePO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
@Repository("invoiceRepository")
public class InvoiceRepository extends AHibernateRepository<InvoicePO, UUID> implements
		IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice, InvoiceID, InvoiceSaveDTO, IExposedFilter> {

	@Override
	public Observable<Collection<Invoice>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Invoice> create(InvoiceSaveDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Void> delete(InvoiceID k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Invoice> update(Invoice t) {
		// TODO Auto-generated method stub
		return null;
	}

}
