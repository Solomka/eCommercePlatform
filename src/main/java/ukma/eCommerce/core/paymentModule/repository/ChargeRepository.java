package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import rx.Observable;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeID;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeEntity;
import ukma.eCommerce.core.paymentModule.repository.po.PaymentPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.IRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

@Repository("chargeRepository")
public class ChargeRepository extends AHibernateRepository<PaymentPO, UUID>
		implements IRepository<Charge, ChargeID, ChargeEntity, IExposedFilter> {

	@Override
	public Observable<Collection<Charge>> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Charge> create(ChargeEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Void> delete(ChargeID k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Observable<Charge> update(Charge t) {
		// TODO Auto-generated method stub
		return null;
	}

}