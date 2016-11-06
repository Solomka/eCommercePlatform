package ukma.eCommerce.core.paymentModule.repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;

public class InvoiceRepository implements IRepository<ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice, String, Invoice, String, IFilter<String>> {

	@Override
	public Observable<Collection<Invoice>> find(@Null IFilter<String> stringIFilter) {
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

}
