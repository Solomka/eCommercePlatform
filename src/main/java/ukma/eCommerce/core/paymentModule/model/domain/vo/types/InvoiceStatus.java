package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

/**
 * <p>
 * enum of invoice statuses
 * </p>
 * 
 * @author Solomka
 *
 */
public enum InvoiceStatus {

	PENDING(1), PAID(2), NOT_PAID(3);

	private int value;

	private InvoiceStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
