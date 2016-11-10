package ukma.eCommerce.core.paymentModule.model.dwo;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;

/**
 * class for charge result representation
 * @author Solomka
 *
 */
public class ChargeExecutionDTO {

	private String message;
	private ChargeStatus chargeStatus;

	public ChargeExecutionDTO() {
	}

	public ChargeExecutionDTO(String message, ChargeStatus chargeStatus) {
		this.message = message;
		this.chargeStatus = chargeStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ChargeStatus getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(ChargeStatus chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	@Override
	public String toString() {
		return "ChargeExecutionDTO [message=" + message + ", chargeStatus=" + chargeStatus + "]";
	}
	
	

}
