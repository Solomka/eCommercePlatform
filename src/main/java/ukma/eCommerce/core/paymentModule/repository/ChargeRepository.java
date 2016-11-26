package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeID;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.PaymentPO;
import ukma.eCommerce.util.repository.AHibernateRepository;
import ukma.eCommerce.util.repository.filter.IExposedFilter;

/**
 * 
 * @author Solomka
 *
 */
@Repository("chargeRepository")
public class ChargeRepository
		extends AHibernateRepository<Charge, ChargeID, ChargeSaveDTO, IExposedFilter, PaymentPO, UUID> {

	@Override
	public Collection<Charge> find(IExposedFilter f) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Charge create(ChargeSaveDTO e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(@NotNull ChargeID chargeID) {

	}

	@Override
	public Charge update(@NotNull Charge charge) {
		return null;
	}

}