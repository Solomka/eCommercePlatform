package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Order;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

/**
 * <p>
 * Readonly proxy object to protect order from
 * changes outside its module
 * </p>
 * Created by Максим on 11/10/2016.
 */
public final class OrderProxy {

    private final Order order;

    public OrderProxy(@NotNull Order order) {
        this.order = Objects.requireNonNull(order);
    }

    public OrderID getId() {
        return order.getId();
    }

    public CustomerID getCustomer() {
        return order.getCustomer();
    }

    public Shipment getShipment() {
        return order.getShipment();
    }

    public Collection<OrderItem> getOrderItems() {
        return order.getOrderItems();
    }

    public OrderStatus getStatus() {
        return order.getStatus();
    }

    public DateTime getCreationDate() {
        return order.getCreationDate();
    }

    public DateTime getFulfilmentDate() {
        return order.getFulfilmentDate();
    }

    @Override
    public int hashCode() {
        return order.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return order.equals(obj);
    }

    @Override
    public String toString() {
        return order.toString();
    }
}
