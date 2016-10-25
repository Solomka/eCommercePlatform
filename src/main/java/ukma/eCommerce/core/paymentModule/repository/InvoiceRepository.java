package ukma.eCommerce.core.paymentModule.repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.util.IRepository;
import ukma.eCommerce.util.IRetrieveCallback;
import ukma.eCommerce.util.IUpdateCallback;
import ukma.eCommerce.util.filter.InvoiceFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

public class InvoiceRepository implements IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice, String, Invoice, InvoiceFilter> {

	@Override
	public Observable<Collection<Invoice>> find(@Null InvoiceFilter filter) {
		return null;
	}

	@Override
	public Observable<Invoice> create(@NotNull Invoice invoice) {
		return null;
	}

	@Override
	public Observable<Void> delete(@NotNull String s) {
		return null;
	}

	@Override
	public Observable<Invoice> update(@NotNull Invoice invoice) {
		return null;
	}

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
