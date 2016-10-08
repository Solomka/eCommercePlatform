package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

public enum ChargeStatus {
	SUCCEEDED(1), PENDING(2), FAILED(3);
	int value;

	private ChargeStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
