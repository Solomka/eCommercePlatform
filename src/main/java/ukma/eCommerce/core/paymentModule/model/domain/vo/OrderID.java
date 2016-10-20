package ukma.eCommerce.core.paymentModule.model.domain.vo;

import javax.validation.constraints.Min;

/**
 * Created by Максим on 10/19/2016.
 */
public final class OrderID {

    @Min(1)
    private final long orderId;

    public OrderID(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderID orderID = (OrderID) o;

        return orderId == orderID.orderId;

    }

    @Override
    public int hashCode() {
        return (int) (orderId ^ (orderId >>> 32));
    }

    @Override
    public String toString() {
        return "OrderID{" +
                "orderId=" + orderId +
                '}';
    }
}
