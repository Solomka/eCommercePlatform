package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;
import ukma.eCommerce.util.filter.InvoiceFilter;

public class InvoiceRepository implements IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice, String, Invoice, InvoiceFilter> {

	@Override
	public void find(InvoiceFilter filter, IRetrieveCallback<Collection<ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice>> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(Invoice form, IRetrieveCallback<ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice> callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String key, IUpdateCallback callback) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Invoice form, String key, IRetrieveCallback<ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice> callback) {
		// TODO Auto-generated method stub

	}

}
