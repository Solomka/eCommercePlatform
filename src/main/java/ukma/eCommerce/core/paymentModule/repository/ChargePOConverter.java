package ukma.eCommerce.core.paymentModule.repository;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;
import ukma.eCommerce.core.paymentModule.repository.po.PaymentPO;

/**
 * 
 * @author Solomka
 *
 */
final class ChargePOConverter {

	private ChargePOConverter() {
		throw new RuntimeException();
	}

	/**
	 * convert from ChargeSaveDTO to PaymentPO
	 * 
	 * @param chargeSaveDTO
	 * @return
	 */

	@NotNull
	static PaymentPO fromChargeSaveDTO(@NotNull ChargeSaveDTO chargeSaveDTO) {

		return new PaymentPO();
	}

	/**
	 * convert from Charge to PaymentPO
	 * 
	 * @param charge
	 * @return
	 */
	@NotNull
	static PaymentPO fromCharge(@NotNull Charge charge) {
		return new PaymentPO();
	}

	/**
	 * convert from PaymentPO to Charge
	 * 
	 * @param PaymentPO
	 * @return
	 */

	@NotNull
	static Charge toCharge(@NotNull PaymentPO paymentPO) {
		return new Charge.Builder().build();
	}

}
