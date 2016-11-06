package ukma.eCommerce.core.paymentModule.repository;

import org.springframework.stereotype.Repository;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import javax.validation.constraints.NotNull;
import java.util.Collection;
@Repository
public class ChargeRepository implements IRepository<Charge, String, Invoice, IExposedFilter> {

	@Override
	public Observable<Collection<Charge>> find(IExposedFilter filter) {
		return null;
	}

	@Override
	public Observable<Charge> create(@NotNull Invoice invoice) {
		return null;
	}

	@Override
	public Observable<Void> delete(@NotNull String s) {
		return null;
	}

	@Override
	public Observable<Charge> update(@NotNull Charge charge) {
		return null;
	}

}