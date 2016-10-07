package ukma.eCommerce.controller.vo.types;

public enum OrderStatus {
	CREATED(1), PAID(2), CANCELED(3), FULFILLED(4), RETURNED(5);
	int value;

	private OrderStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}