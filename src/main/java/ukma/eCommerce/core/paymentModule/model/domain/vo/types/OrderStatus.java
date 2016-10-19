package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

/**
 * <p>
 * enum of order statuses
 * </p>
 *
 * @author Solomka
 */
public enum OrderStatus {

    CREATED(1), PAID(2), CANCELED(3), RETURNED(4), ISSUED_PARTIALLY(5), ISSUED(6);

    private int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static OrderStatus forValue(int value) {

        for (final OrderStatus status : OrderStatus.values()) {
            if (status.getValue() == value) return status;
        }

        return null;
    }

}