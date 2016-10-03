package ukma.eCommerce.repository;

import java.util.Collection;

import ukma.eCommerce.controller.vo.InvoiceVO;
import ukma.eCommerce.domain.bo.Invoice;
import ukma.eCommerce.repository.filter.InvoiceFilter;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;

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
