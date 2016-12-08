package ukma.eCommerce.core.paymentModule.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.Specification;
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

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Charge> find(IExposedFilter f) {
		return findAllBySpecification((Specification<PaymentPO>) f.toFilter()).stream()
				.map(paymentPO -> ChargePOConverter.toCharge(paymentPO)).collect(Collectors.toList());
	}

	@Override
	public Charge create(ChargeSaveDTO chargeSaveDTO) {
		final PaymentPO paymentPO = ChargePOConverter.fromChargeSaveDTO(chargeSaveDTO);
		savePO(paymentPO);
		return ChargePOConverter.toCharge(paymentPO);
	}

	@Override
	public Charge update(@NotNull Charge charge) {
		final PaymentPO paymentPO = updatePO(ChargePOConverter.fromCharge(charge));
		return ChargePOConverter.toCharge(paymentPO);
	}

	@Override
	public void delete(@NotNull ChargeID chargeID) {
		deletePOById(chargeID.getId());
	}

}