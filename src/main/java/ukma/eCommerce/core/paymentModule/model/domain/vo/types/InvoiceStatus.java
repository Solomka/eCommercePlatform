package ukma.eCommerce.core.paymentModule.model.domain.vo.types;

/**
 * <p>
 * enum of invoice statuses
 * </p>
 *
 * @author Solomka
 */
public enum InvoiceStatus {

    PENDING(1), PAID(2), NOT_PAID(3);

    private int value;

    InvoiceStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static InvoiceStatus forValue(int value) {

        for (final InvoiceStatus status : InvoiceStatus.values()) {
            if (status.getValue() == value) return status;
        }

        return null;
    }

}
