package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

/**
 * <p>
 * enum of charge (payment) statuses
 * </p>
 *
 * @author Solomka
 */
public enum ChargeStatus {

	PENDING(1), SUCCEEDED(2), FAILED(3);

	private int value;

	//class/package access level
	ChargeStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * Returns charge status for a given int value
	 * 
	 * @param value
	 *            value describing charge status
	 * @return null if such status wasn't found
	 */
	public static ChargeStatus forValue(int value) {

		for (final ChargeStatus status : ChargeStatus.values()) {
			if (status.getValue() == value)
				return status;
		}

		return null;
	}

}
