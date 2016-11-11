package ukma.eCommerce.core.paymentModule.repository;

import org.springframework.stereotype.Repository;
import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeID;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.PaymentPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

/**
 * 
 * @author Solomka
 *
 */
@Repository("chargeRepository")
public class ChargeRepository extends AHibernateRepository<PaymentPO, UUID>
		implements IRepository<Charge, ChargeID, ChargeSaveDTO, IExposedFilter> {

	@Override
	public Observable<Collection<Charge>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Charge> create(ChargeSaveDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Void> delete(@NotNull ChargeID chargeID) {
		return null;
	}

	@Override
	public Observable<Charge> update(@NotNull Charge charge) {
		return null;
	}

}