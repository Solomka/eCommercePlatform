package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

/**
 * <p>
 * enum of order statuses
 * </p>
 *
 * @author Solomka
 */
public enum OrderStatus {

	CREATED(1), PAID(2), CANCELED(3), RETURNED(4), FULFILLED_PARTIALLY(5), ISSUED_PARTIALLY(6), FULFILLED(6), ISSUED(7);

	private int value;

	OrderStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * Returns order status for a given int value
	 * 
	 * @param value
	 *            value describing order status
	 * @return null if such status wasn't found
	 */
	public static OrderStatus forValue(int value) {

		for (final OrderStatus status : OrderStatus.values()) {
			if (status.getValue() == value)
				return status;
		}

		return null;
	}

}