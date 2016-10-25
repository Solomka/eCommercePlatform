package ukma.eCommerce.core.paymentModule.model.domain.bo;

import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.CANCELED;
import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.CREATED;
import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.FULFILLED;
import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.FULFILLED_PARTIALLY;
import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.ISSUED;
import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.ISSUED_PARTIALLY;
import static ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus.PAID;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Shipment;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ShipmentDetails;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ShipmentStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * <p>
 * Aggregate root that represents Order
 * </p>
 *
 * @author Solomka
 */
public final class Order {

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
	}

	public OrderID getId() {
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

	public void changeShipment(Shipment shipment) {

		if (!ValidationUtil.isValid(shipment)) {
			throw new IllegalArgumentException("shipment isn't valid");
		}

		this.shipment = shipment;
	}

	/**
	 * change shipment address
	 * 
	 * @param details
	 */
	public void changeShipmentDetails(ShipmentDetails details) {
		if (!ValidationUtil.isValid(details)) {
			throw new IllegalArgumentException("shipment detials are not valid");
		}
		Shipment.Builder shipmentBuilder = new Shipment.Builder(getShipment());
		this.shipment = shipmentBuilder.setShipmentDetails(details).build();

	}

	/**
	 * change shipment status If ShipmentStatus == DELIVERED_PARTIALLY it means
	 * that OrderStatus == FULFILLED_PARTIALLY if ShipmentStatus == DELIVERED it
	 * means that OrderStatus == FULFILLED
	 * 
	 * @param status
	 */
	public void changeShipmentStatus(ShipmentStatus status) {

		Shipment.Builder shipmentBuilder = new Shipment.Builder(getShipment());
		DateTime dateTime = DateTime.now();

		switch (status) {
		case DELIVERED_PARTIALLY:
			this.shipment = shipmentBuilder.setStatus(status).setDeliveryDate(dateTime).build();
			fulfillOrder(dateTime, OrderStatus.FULFILLED_PARTIALLY);

			break;
		case DELIVERED:
			this.shipment = shipmentBuilder.setStatus(status).setDeliveryDate(dateTime).build();
			fulfillOrder(dateTime, OrderStatus.FULFILLED);
			break;
		default:
			this.shipment = shipmentBuilder.setStatus(status).build();

		}

	}

	public void changeOrderStatus(OrderStatus status) {
		this.status = checkOrderStatus(fulfilmentDate, status);
	}

	public void fulfillOrder(DateTime dateTime, OrderStatus orderStatus) {

		if (creationDate.compareTo(Objects.requireNonNull(dateTime, "datetime == null")) > 0)
			throw new IllegalArgumentException("order created later then fulfilled");
		/**
		 * fulfilmentDate can be changed several times 'cause first order can be
		 * fulfilled_partially and we will change fulfillment date once and then
		 * order will be fulfilled and we will change fulfillment date second
		 * time
		 */
		/*
		 * if (status != null && dateTime.compareTo(fulfilmentDate) != 0) throw
		 * new IllegalStateException("order can be fulfilled only once");
		 */
		this.fulfilmentDate = dateTime;
		changeOrderStatus(status);

	}

	/**
	 * If status == CREATED || CANCELED || PAID then fulfilmentDate == NULL If
	 * status == FULFILLED_PARTIALLY || ISSUED_PARTIALLY || FULFILLED || ISSUED
	 * then fulfilmentDate != NULL
	 * 
	 * Attention!! If status == RETURNED and fulfilmentDate == NULL - customer
	 * hadn't received his order during specific period of time and this order
	 * was returned
	 * 
	 * If status == RETURNED and fulfilmentDate != NULL - customer had received
	 * his order AND he returned it because of the some reason (e.g. defective
	 * product)
	 * 
	 * @param fulfilmentDate
	 * @param status
	 * @return
	 */

	private OrderStatus checkOrderStatus(DateTime fulfilmentDate, OrderStatus status) {

		Objects.requireNonNull(status);

		if (fulfilmentDate != null && (status == CREATED || status == CANCELED || status == PAID))
			throw new IllegalArgumentException("order cannot be created/canceled/paid and fulfilled at the same time");

		if (fulfilmentDate == null && (status == FULFILLED_PARTIALLY || status == ISSUED_PARTIALLY
				|| status == FULFILLED || status == ISSUED))
			throw new IllegalArgumentException(
					String.format("order doesn't have fulfilment date, but has status %s", status));

		if (status == ISSUED_PARTIALLY && (this.status == null || this.status != FULFILLED_PARTIALLY)) {
			throw new IllegalArgumentException(String
					.format("order status can't be ISSUED_PARTIALLY if it wasn't FULFILLED_PARTIALLY first", status));

		}
		if (status == ISSUED && (this.status == null || this.status != FULFILLED)) {
			throw new IllegalArgumentException(
					String.format("order status can't be ISSUED if it wasn't FULFILLED first", status));

		}

		return status;
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
