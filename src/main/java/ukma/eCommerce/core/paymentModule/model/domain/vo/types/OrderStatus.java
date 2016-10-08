package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

public enum OrderStatus {
	CREATED(1), PAID(2), CANCELED(3), FULFILLED(4), RETURNED(5);
	int value;

	OrderStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}