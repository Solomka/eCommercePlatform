package ukma.eCommerce.core.paymentModule.model.dwo;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItem;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Shipment;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * Created by Максим on 11/6/2016.
 */
public final class OrderSaveDTO {

	private final CustomerID customer;
	private final Shipment shipment;
	private final Collection<OrderItem> orderItems;
	private final OrderStatus status;
	private final DateTime creationDate;
	private final DateTime fulfilmentDate;

	public static final class Builder implements IBuilder<OrderSaveDTO> {

		private CustomerID customer;
		private Shipment shipment;
		private Collection<OrderItem> orderItems;
		private OrderStatus orderStatus;
		private DateTime creationDate;
		private DateTime fulfilmentDate;

		public Builder() {
		}

		public Builder(final OrderSaveDTO orderSaveDTO) {

			Objects.requireNonNull(orderSaveDTO, "order must not be null");

			setCustomer(orderSaveDTO.getCustomer()).setShipment(orderSaveDTO.getShipment())
					.setOrderItems(orderSaveDTO.getOrderItems()).setOrderStatus(orderSaveDTO.getStatus())
					.setCreationDate(orderSaveDTO.getCreationDate())
					.setFulfilmentDate(orderSaveDTO.getFulfilmentDate());
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
		public OrderSaveDTO build() {
			return new OrderSaveDTO(this);
		}

	}

	private OrderSaveDTO(Builder builder) {

		Objects.requireNonNull(builder, "builder must no be null");

		if (Objects.requireNonNull(builder.getCreationDate(), "creationDate  must not be null").isAfterNow())
			throw new IllegalArgumentException("creation time should be earlierthan current one");

		if (builder.getFulfilmentDate() != null && builder.getFulfilmentDate().compareTo(builder.getCreationDate()) < 0)
			throw new IllegalArgumentException("fulfilment date is earlier than creation date");

		final Collection<OrderItem> orderItems = ValidationUtil.validate(builder.getOrderItems());

		this.customer = ValidationUtil.validate(builder.getCustomer());
		this.shipment = ValidationUtil.validate(builder.getShipment());
		this.orderItems = Collections.unmodifiableCollection(orderItems);
		this.creationDate = builder.getCreationDate();

		this.fulfilmentDate = builder.getFulfilmentDate();
		this.status = builder.getOrderStatus();
	}

	public CustomerID getCustomer() {
		return customer;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public Collection<OrderItem> getOrderItems() {
		return orderItems;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public DateTime getFulfilmentDate() {
		return fulfilmentDate;
	}
}
