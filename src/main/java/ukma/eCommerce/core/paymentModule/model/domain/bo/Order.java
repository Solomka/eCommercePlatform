package ukma.eCommerce.core.paymentModule.model.domain.bo;


import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.*;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.*;

/**
 * <p>
 * Aggregate root that represents order
 * </p>
 *
 * @author Solomka
 */
public final class Order {

    private final long id;
    private final CustomerID customer;
    private Shipment shipment;
    private final Collection<OrderItem> orderItems;
    private OrderStatus status;
    private final DateTime creationDate;
    private DateTime fulfilmentDate;

    /**
     * builder that creates immutable instance of {@linkplain Order}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<Order> {

        private long id;
        private CustomerID customer;
        private Shipment shipment;
        private Collection<OrderItem> orderItems;
        private OrderStatus orderStatus;
        private DateTime creationDate;
        private DateTime fulfilmentDate;

        public Builder() {
        }

        public Builder(final Order order) {

            Objects.requireNonNull(order, "order must not be null");

            setId(order.getId()).setCustomer(order.getCustomer()).setShipment(order.getShipment())
                    .setOrderItems(order.getOrderItems()).setOrderStatus(order.getStatus())
                    .setCreationDate(order.getCreationDate()).setFulfilmentDate(order.getFulfilmentDate());
        }

        public long getId() {
            return id;
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public CustomerID getCustomer() {
            return customer;
        }

        public Builder setCustomer(CustomerID customer) {
            this.customer = customer;
            return this;
        }

        public Shipment getShipment() {
            return shipment;
        }

        public Builder setShipment(Shipment shipment) {
            this.shipment = shipment;
            return this;
        }

        public Collection<OrderItem> getOrderItems() {
            return orderItems;
        }

        public Builder setOrderItems(Collection<OrderItem> orderItems) {
            this.orderItems = orderItems == null ? null : Collections.unmodifiableCollection(orderItems);
            return this;
        }

        public OrderStatus getOrderStatus() {
            return orderStatus;
        }

        public Builder setOrderStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public DateTime getCreationDate() {
            return creationDate;
        }

        public Builder setCreationDate(DateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public DateTime getFulfilmentDate() {
            return fulfilmentDate;
        }

        public Builder setFulfilmentDate(DateTime fulfilmentDate) {
            this.fulfilmentDate = fulfilmentDate;
            return this;
        }

        @Override
        public Order build() {
            return new Order(this);
        }

    }

    private Order(Builder builder) {

        Objects.requireNonNull(builder, "builder must no be null");

        if (builder.getId() < 1)
            throw new IllegalArgumentException("id < 1");

        if (Objects.requireNonNull(builder.getCreationDate(), "creationDate must not be null").isAfterNow())
            throw new IllegalArgumentException("creation time should be earlier than current one");

        if (builder.getFulfilmentDate() != null &&
                builder.getFulfilmentDate().compareTo(builder.getCreationDate()) < 0)
            throw new IllegalArgumentException("fulfilment date is earlier than creation date");

        final Collection<OrderItem> orderItems = ValidationUtil.validate(builder.getOrderItems());

        this.id = builder.getId();
        this.customer = ValidationUtil.validate(builder.getCustomer());
        this.shipment = ValidationUtil.validate(builder.getShipment());
        this.orderItems = new ArrayList<>(orderItems);
        this.creationDate = builder.getCreationDate();

        this.fulfilmentDate = builder.getFulfilmentDate();
        this.status = Order.checkStatus(fulfilmentDate, builder.getOrderStatus());
    }

    public long getId() {
        return id;
    }

    public CustomerID getCustomer() {
        return customer;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public Collection<OrderItem> getOrderItems() {
        return Collections.unmodifiableCollection(orderItems);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public DateTime getFulfilmentDate() {
        return fulfilmentDate;
    }

    public void setDeliverTo(Shipment shipment) {

        if (!ValidationUtil.isValid(shipment)) {
            throw new IllegalArgumentException("shipment isn't valid");
        }

        this.shipment = shipment;
    }

    public void addOrderItem(OrderItem orderItem) {

        if (!ValidationUtil.isValid(orderItem)) {
            throw new IllegalArgumentException("orderItem isn't valid");
        }

        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {

        if (!ValidationUtil.isValid(orderItem)) {
            throw new IllegalArgumentException("orderItem isn't valid");
        }

        orderItems.remove(orderItem);
    }

    public void changeStatus(OrderStatus status) {
        this.status = Order.checkStatus(fulfilmentDate, status);
    }

    public void fulfill(DateTime dateTime) {

        if(creationDate.compareTo(Objects.requireNonNull(dateTime, "datetime == null")) > 0)
            throw new IllegalArgumentException("order created later than fulfilled");

        if(status != null && dateTime.compareTo(fulfilmentDate) != 0)
            throw new IllegalStateException("order can be fulfilled only once");

        this.status = OrderStatus.CREATED;
        this.fulfilmentDate = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!customer.equals(order.customer)) return false;
        if (shipment != null ? !shipment.equals(order.shipment) : order.shipment != null) return false;
        if (!orderItems.equals(order.orderItems)) return false;
        if (status != order.status) return false;
        if (!creationDate.equals(order.creationDate)) return false;
        return fulfilmentDate != null ? fulfilmentDate.equals(order.fulfilmentDate) : order.fulfilmentDate == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + customer.hashCode();
        result = 31 * result + (shipment != null ? shipment.hashCode() : 0);
        result = 31 * result + orderItems.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + (fulfilmentDate != null ? fulfilmentDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", shipment=" + shipment +
                ", orderItems=" + orderItems +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", fulfilmentDate=" + fulfilmentDate +
                '}';
    }

    private static OrderStatus checkStatus(DateTime fulfilmentDate, OrderStatus status) {

        Objects.requireNonNull(status);

        if(fulfilmentDate != null && (status == CREATED || status == CANCELED))
            throw new IllegalArgumentException("order cannot be created or canceled and fulfilled at same time");

        if(fulfilmentDate == null &&
                (status == PAID || status == RETURNED || status == ISSUED_PARTIALLY || status == ISSUED))
            throw new IllegalArgumentException(
                    String.format("order doesn't have fulfilment date, but has status %s", status)
            );

        return status;
    }

}
