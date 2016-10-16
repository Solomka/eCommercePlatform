package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

/**
 * <p>
 * enum of shipment statuses
 * </p>
 *
 * @author Solomka
 */
public enum ShipmentStatus {

    IN_SENDER_WAREHOUSE(1), IN_DELIVERY(2), DELIVERED_PARTIALLY(3), DELIVERED(4);

    private int value;

    ShipmentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static ShipmentStatus forValue(int value) {

        for (final ShipmentStatus status : ShipmentStatus.values()) {
            if (status.getValue() == value) return status;
        }

        return null;
    }

}
