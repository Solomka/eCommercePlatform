package ukma.eCommerce.core.paymentModule.repository;

import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceVO;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.util.filter.InvoiceFilter;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;

import java.util.Collection;

public class InvoiceRepository implements IRepository<Invoice, String, InvoiceVO, InvoiceFilter> {

	@Override
	public void find(InvoiceFilter filter, IRetrieveCallback<Collection<Invoice>> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(InvoiceVO form, IRetrieveCallback<Invoice> callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String key, IUpdateCallback callback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(InvoiceVO form, String key, IRetrieveCallback<Invoice> callback) {
		// TODO Auto-generated method stub
		
	}

}
