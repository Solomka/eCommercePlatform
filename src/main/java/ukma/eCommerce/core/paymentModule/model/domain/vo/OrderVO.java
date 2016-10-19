package ukma.eCommerce.core.paymentModule.model.domain.vo;


import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerVO;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerVO;
import ukma.eCommerce.util.IBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * <p>
 * value object that represents order
 * </p>
 *
 * @author Solomka
 */
public final class OrderVO {
    @NotNull
    @Valid
    private final CustomerVO customer;
    @NotNull
    @Valid
    private final SellerVO seller;
    @NotNull
    @Valid
    private final ShipmentVO shipment;
    @NotNull
    @Valid
    private final Collection<OrderItemVO> orderItems;
    @NotNull
    private final OrderStatus orderStatus;
    @NotNull
    @Past
    private final DateTime creationDate;
    @Past
    private final DateTime fulfilmentDate;

    /**
     * builder that creates immutable instance of {@linkplain OrderVO}
     *
     * @author Solomka
     */

    public static class Builder implements IBuilder<OrderVO> {

        private CustomerVO customer;
        private SellerVO seller;
        private ShipmentVO shipment;
        private Collection<OrderItemVO> orderItems;
        private OrderStatus orderStatus;
        private DateTime creationDate;
        private DateTime fulfilmentDate;

        public Builder() {
        }

        public Builder(final OrderVO order) {

            Objects.requireNonNull(order, "order must not be null");

            setCustomer(order.getCustomer()).setSeller(order.getSeller()).setShipment(order.getShipment())
                    .setOrderItems(order.getOrderItems()).setOrderStatus(order.getOrderStatus())
                    .setCreationDate(order.getCreationDate()).setFulfilmentDate(order.getFulfilmentDate());
        }

        public CustomerVO getCustomer() {
            return customer;
        }

        public Builder setCustomer(CustomerVO customer) {
            this.customer = customer;
            return this;
        }

        public SellerVO getSeller() {
            return seller;
        }

        public Builder setSeller(SellerVO seller) {
            this.seller = seller;
            return this;
        }

        public ShipmentVO getShipment() {
            return shipment;
        }

        public Builder setShipment(ShipmentVO shipment) {
            this.shipment = shipment;
            return this;
        }

        public Collection<OrderItemVO> getOrderItems() {
            return orderItems;
        }

        /**
         * unmodifiable collection of order items
         *
         * @param orderItems
         * @return
         */
        public Builder setOrderItems(Collection<OrderItemVO> orderItems) {
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
        public OrderVO build() {
            return new OrderVO(this);
        }

    }

    private OrderVO(Builder builder) {

    	Objects.requireNonNull(builder, "builder must no be null");

		final Collection<OrderItemVO> orderItems = Objects.requireNonNull(builder.getOrderItems(),
				"orderItems must no be null");

        this.customer = Objects.requireNonNull(builder.getCustomer(), "customer must not be null");
        this.seller = Objects.requireNonNull(builder.getSeller(), "seller must not be null");
        this.shipment = Objects.requireNonNull(builder.getShipment(), "shipment must not be null");
        this.orderItems = Collections.unmodifiableCollection(orderItems);
        this.orderStatus = Objects.requireNonNull(builder.getOrderStatus(), "orderStatus must not be null");
        this.creationDate = Objects.requireNonNull(builder.getCreationDate(), "creationDate must not be null");
        this.fulfilmentDate = builder.getFulfilmentDate();
    }

    public CustomerVO getCustomer() {
        return customer;
    }

    public SellerVO getSeller() {
        return seller;
    }

    public ShipmentVO getShipment() {
        return shipment;
    }

    public Collection<OrderItemVO> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public DateTime getFulfilmentDate() {
        return fulfilmentDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        result = prime * result + ((fulfilmentDate == null) ? 0 : fulfilmentDate.hashCode());
        result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
        result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
        result = prime * result + ((seller == null) ? 0 : seller.hashCode());
        result = prime * result + ((shipment == null) ? 0 : shipment.hashCode());
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
        OrderVO other = (OrderVO) obj;
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
        if (orderItems == null) {
            if (other.orderItems != null)
                return false;
        } else if (!orderItems.equals(other.orderItems))
            return false;
        if (orderStatus != other.orderStatus)
            return false;
        if (seller == null) {
            if (other.seller != null)
                return false;
        } else if (!seller.equals(other.seller))
            return false;
        if (shipment == null) {
            if (other.shipment != null)
                return false;
        } else if (!shipment.equals(other.shipment))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "OrderVO [customer=" + customer + ", seller=" + seller + ", shipment=" + shipment + ", orderItems="
                + orderItems + ", orderStatus=" + orderStatus + ", creationDate=" + creationDate + ", fulfilmentDate="
                + fulfilmentDate + "]";
    }

}
