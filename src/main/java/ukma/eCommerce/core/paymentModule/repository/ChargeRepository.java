package ukma.eCommerce.core.paymentModule.repository;

import org.springframework.stereotype.Repository;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Collection;
@Repository
public class ChargeRepository implements IRepository<Charge, String, Invoice, String, IFilter<String>> {

	@Override
	public Observable<Collection<Charge>> find(@Null IFilter<String> filter) {
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