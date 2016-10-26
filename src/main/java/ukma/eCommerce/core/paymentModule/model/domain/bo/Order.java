package ukma.eCommerce.core.paymentModule.model.domain.bo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.*;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.*;

/**
 * <p>
 * Aggregate root that represents Order
 * </p>
 *
 * @author Solomka
 */
public final class Order {

    private final ReadWriteLock shipmentReadWriteLock;
    private final OrderID id;
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

        private OrderID id;
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

        public OrderID getId() {
            return id;
        }

        public Builder setId(OrderID id) {
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

        if (Objects.requireNonNull(builder.getCreationDate(), "creationDate must not be null").isAfterNow())
            throw new IllegalArgumentException("creation time should be earlier than current one");

        if (builder.getFulfilmentDate() != null && builder.getFulfilmentDate().compareTo(builder.getCreationDate()) < 0)
            throw new IllegalArgumentException("fulfilment date is earlier than creation date");

        final Collection<OrderItem> orderItems = ValidationUtil.validate(builder.getOrderItems());

        this.id = ValidationUtil.validate(builder.getId());
        this.customer = ValidationUtil.validate(builder.getCustomer());
        this.shipment = ValidationUtil.validate(builder.getShipment());
        this.orderItems = new ArrayList<>(orderItems);
        this.creationDate = builder.getCreationDate();

        this.fulfilmentDate = builder.getFulfilmentDate();
        this.status = checkOrderStatus(fulfilmentDate, builder.getOrderStatus());
        this.shipmentReadWriteLock = new ReentrantReadWriteLock();
    }

    public OrderID getId() {
        return id;
    }

    public CustomerID getCustomer() {
        return customer;
    }

    public Shipment getShipment() {

        shipmentReadWriteLock.readLock().lock();

        try {
            return shipment;
        } finally {
            shipmentReadWriteLock.readLock().unlock();
        }
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

    public void addOrderItem(OrderItem orderItem) {

        if (!ValidationUtil.isValid(orderItem))
            throw new IllegalArgumentException("orderItem isn't valid");

        orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {

        if (!ValidationUtil.isValid(orderItem)) {
            throw new IllegalArgumentException("orderItem isn't valid");
        }

        orderItems.remove(orderItem);
    }

    public void changeDeliveryDate(DateTime date) {
        setShipment(new Shipment.Builder(getShipment()).setDeliveryDate(date).build());
    }

    public void changeDeliveryPrice(Price price) {
        setShipment(new Shipment.Builder(getShipment()).setPrice(price).build());
    }

    public void changeDeliveryService(String deliveryService) {
        setShipment(new Shipment.Builder(getShipment()).setDeliveryService(deliveryService).build());
    }

    /**
     * change shipment address
     *
     * @param details
     */
    public void changeShipmentDetails(ShipmentDetails details) {
        setShipment(new Shipment.Builder(getShipment()).setShipmentDetails(details).build());
    }

    /**
     * change shipment status. Allowed transition matrix looks like <br>
     * <table>
     * <tr><td>Shipment\Order</td><td>CREATED</td><td>PAID</td><td>CANCELED</td><td>RETURNED</td><td>FULFILLED PARTIALLY</td><td>ISSUED PARTIALLY</td><td>FULFILLED</td><td>ISSUED</td></tr>
     * <tr><td>IN_SENDER_WAREHOUSE</td><td>yes</td><td>yes</td><td>yes</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>IN_DELIVERY</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED_PARTIALLY</td><td>no</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>yes</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>yes</td><td>yes</td></tr>
     * </table>
     *
     * @param status status to be set
     */
    public void changeShipmentStatus(ShipmentStatus status) {
        changeStatus(getFulfilmentDate(), getStatus(), status);
    }

    /**
     * Changes order status. Allowed transition matrix looks like <br>
     * <table>
     * <tr><td>Shipment\Order</td><td>CREATED</td><td>PAID</td><td>CANCELED</td><td>RETURNED</td><td>FULFILLED PARTIALLY</td><td>ISSUED PARTIALLY</td><td>FULFILLED</td><td>ISSUED</td></tr>
     * <tr><td>IN_SENDER_WAREHOUSE</td><td>yes</td><td>yes</td><td>yes</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>IN_DELIVERY</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED_PARTIALLY</td><td>no</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>yes</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>yes</td><td>yes</td></tr>
     * </table>
     *
     * @param fulfilmentDate fulfilment date to set
     * @param orderStatus    order status to set
     * @param shipmentStatus shipmentStatus to set
     * @throws IllegalArgumentException if one of parameters is invalid
     */
    public void changeStatus(DateTime fulfilmentDate, OrderStatus orderStatus, ShipmentStatus shipmentStatus) {

        shipmentReadWriteLock.writeLock().lock();

        try {

            checkState(shipmentStatus, orderStatus);
            checkOrderStatus(fulfilmentDate, orderStatus);
            checkShipmentStatus(shipmentStatus);

            this.shipment = new Shipment.Builder(getShipment()).setStatus(shipmentStatus).build();
            this.fulfilmentDate = fulfilmentDate;
            this.status = orderStatus;
        } finally {
            shipmentReadWriteLock.writeLock().unlock();
        }
    }

    /**
     * Changes order status. Allowed transition matrix looks like <br>
     * <table>
     * <tr><td>Shipment\Order</td><td>CREATED</td><td>PAID</td><td>CANCELED</td><td>RETURNED</td><td>FULFILLED PARTIALLY</td><td>ISSUED PARTIALLY</td><td>FULFILLED</td><td>ISSUED</td></tr>
     * <tr><td>IN_SENDER_WAREHOUSE</td><td>yes</td><td>yes</td><td>yes</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>IN_DELIVERY</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED_PARTIALLY</td><td>no</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>yes</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>yes</td><td>yes</td></tr>
     * </table>
     * @param status status to set
     */
    public void changeOrderStatus(final OrderStatus status) {
        changeStatus(getFulfilmentDate(), status, getShipment().getStatus());
    }

    /**
     * Replaces current shipment with new new one. Allowed transition matrix looks like <br>
     * <table>
     * <tr><td>Shipment\Order</td><td>CREATED</td><td>PAID</td><td>CANCELED</td><td>RETURNED</td><td>FULFILLED PARTIALLY</td><td>ISSUED PARTIALLY</td><td>FULFILLED</td><td>ISSUED</td></tr>
     * <tr><td>IN_SENDER_WAREHOUSE</td><td>yes</td><td>yes</td><td>yes</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>IN_DELIVERY</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED_PARTIALLY</td><td>no</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>yes</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>yes</td><td>yes</td></tr>
     * </table>
     *
     * @param shipment shipment to set
     */
    private void setShipment(final Shipment shipment) {

        if (!ValidationUtil.isValid(shipment))
            throw new IllegalArgumentException("shipment isn't valid");

        shipmentReadWriteLock.writeLock().lock();

        try {
            checkShipmentStatus(shipment.getStatus());
            checkState(shipment.getStatus(), status);
            this.shipment = shipment;
        } finally {
            shipmentReadWriteLock.writeLock().unlock();
        }
    }

    /**
     * If status == CREATED || CANCELED || PAID then fulfilmentDate == NULL If
     * status == FULFILLED_PARTIALLY || ISSUED_PARTIALLY || FULFILLED || ISSUED
     * then fulfilmentDate != NULL
     * <p>
     * Attention!! If status == RETURNED and fulfilmentDate == NULL - customer
     * hadn't received his order during specific period of time and this order
     * was returned
     * <p>
     * If status == RETURNED and fulfilmentDate != NULL - customer had received
     * his order AND he returned it because of the some reason (e.g. defective
     * product)
     *
     * @param fulfilmentDate
     * @param status
     * @return
     */

    private OrderStatus checkOrderStatus(final DateTime fulfilmentDate, final OrderStatus status) {

        Objects.requireNonNull(status);

        if (fulfilmentDate != null && (status == CREATED || status == CANCELED || status == PAID))
            throw new IllegalArgumentException("order cannot have status created/canceled or paid at the same time when fulfilled");

        if (fulfilmentDate == null && (status == FULFILLED_PARTIALLY || status == ISSUED_PARTIALLY
                || status == FULFILLED || status == ISSUED))
            throw new IllegalArgumentException(
                    String.format("order doesn't have fulfilment date, but has status %s", status));

        checkOrderStatusTransition(status);

        return status;
    }

    /**
     * Checks order status to be set. Allowed transitions are CREATED -> PAID -> FULFILLED/PARTIALLY
     * -> ISSUED/PARTIALLY. If status is CREATED, then new status can be CANCELED, in other
     * cases - RETURNED
     *
     * @param status new status to check
     */
    private void checkOrderStatusTransition(final OrderStatus status) {

        Objects.requireNonNull(status);

        if (status == CANCELED && this.status != CREATED)
            throw new IllegalArgumentException("to cancel order it should have status created");

        if (status == RETURNED && this.status == CREATED)
            throw new IllegalArgumentException("cannot set status returned when status was created");

        if (status == PAID && this.status != CREATED)
            throw new IllegalArgumentException("should be created before paid");

        if ((status == FULFILLED || status == FULFILLED_PARTIALLY) && this.status != PAID)
            throw new IllegalArgumentException("should be fulfilled before paid");

        if ((status == ISSUED || status == ISSUED_PARTIALLY) && this.status != PAID)
            throw new IllegalArgumentException("should be paid before issued");
    }

    /**
     * Checks shipment status to be set. Allowed transitions are IN_SENDER_WAREHOUSE ->
     * IN_DELIVERY -> DELIVERED/PARTIALLY
     *
     * @param status new status to check
     */
    private ShipmentStatus checkShipmentStatus(final ShipmentStatus status) {

        Objects.requireNonNull(status);

        if (status != ShipmentStatus.IN_DELIVERY && shipment.getStatus() == ShipmentStatus.IN_SENDER_WAREHOUSE)
            throw new IllegalStateException("should be in sender warehouse before in delivery");

        if ((status == ShipmentStatus.DELIVERED || status == ShipmentStatus.DELIVERED_PARTIALLY)
                && shipment.getStatus() != ShipmentStatus.IN_DELIVERY)
            throw new IllegalStateException("order should be IN_DELIVERY before DELIVERED_PARTIALLY or DELIVERED");

        return status;
    }

    /**
     * <table>
     * <tr><td>Shipment\Order</td><td>CREATED</td><td>PAID</td><td>CANCELED</td><td>RETURNED</td><td>FULFILLED PARTIALLY</td><td>ISSUED PARTIALLY</td><td>FULFILLED</td><td>ISSUED</td></tr>
     * <tr><td>IN_SENDER_WAREHOUSE</td><td>yes</td><td>yes</td><td>yes</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>IN_DELIVERY</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>no</td><td>no</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED_PARTIALLY</td><td>no</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>yes</td><td>no</td><td>no</td></tr>
     * <tr><td>DELIVERED</td><td>no</td><td>no</td><td>no</td><td>yes</td><td>no</td><td>yes</td><td>yes</td><td>yes</td></tr>
     * </table>
     *
     * @param shipmentStatus
     * @param orderStatus
     */
    private void checkState(ShipmentStatus shipmentStatus, OrderStatus orderStatus) {

        if (shipmentStatus == ShipmentStatus.IN_SENDER_WAREHOUSE && (
                orderStatus == OrderStatus.FULFILLED_PARTIALLY || orderStatus == OrderStatus.FULFILLED ||
                        orderStatus == OrderStatus.ISSUED || orderStatus == OrderStatus.ISSUED_PARTIALLY))
            throw new IllegalStateException("shouldn't be IN_SENDER_WAREHOUSE while FULFILLED/ISSUED/PARTIALLY");

        if (shipmentStatus == ShipmentStatus.IN_DELIVERY && orderStatus != OrderStatus.PAID &&
                orderStatus == OrderStatus.RETURNED)
            throw new IllegalStateException("while IN_DELIVERY should have status PAID/RETURNED");

        if (shipmentStatus == ShipmentStatus.DELIVERED_PARTIALLY && orderStatus != OrderStatus.FULFILLED_PARTIALLY &&
                orderStatus != OrderStatus.ISSUED_PARTIALLY)
            throw new IllegalStateException("while DELIVERED_PARTIALLY should have status FULFILLED_PARTIALLY/ISSUED_PARTIALLY");

        if (shipmentStatus == ShipmentStatus.DELIVERED && orderStatus != RETURNED && orderStatus != ISSUED_PARTIALLY
                && orderStatus != FULFILLED && orderStatus != ISSUED)
            throw new IllegalStateException("while DELIVERED should have status RETURNED/ISSUED_PARTIALLY/FULFILLED/ISSUED");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((fulfilmentDate == null) ? 0 : fulfilmentDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
        result = prime * result + ((shipment == null) ? 0 : shipment.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (customer == null) {
            if (other.customer != null)
                return false;
        } else if (!customer.equals(other.customer))
            return false;
        if (fulfilmentDate == null) {
            if (other.fulfilmentDate != null)
                return false;
        } else if (!fulfilmentDate.equals(other.fulfilmentDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (orderItems == null) {
            if (other.orderItems != null)
                return false;
        } else if (!orderItems.equals(other.orderItems))
            return false;
        if (shipment == null) {
            if (other.shipment != null)
                return false;
        } else if (!shipment.equals(other.shipment))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", customer=" + customer + ", shipment=" + shipment + ", orderItems=" + orderItems
                + ", status=" + status + ", creationDate=" + creationDate + ", fulfilmentDate=" + fulfilmentDate + "]";
    }

}
