package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

/**
 * <p>
 * enum of charge (payment) statuses
 * </p>
 * 
 * @author Solomka
 *
 */
public enum ChargeStatus {

	PENDING(1), SUCCEEDED(2), FAILED(3);

	private int value;

	private ChargeStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
