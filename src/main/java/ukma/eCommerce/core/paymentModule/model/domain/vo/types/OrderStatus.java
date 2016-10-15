package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

/**
 * <p>
 * enum of order statuses
 * </p>
 * 
 * @author Solomka
 *
 */
public enum OrderStatus {

	CREATED(1), PAID(2), CANCELED(3), RETURNED(4), ISSUED_PARTIALLY(5), ISSUED(6);

	private int value;

	private OrderStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}