package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerVO;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerVO;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.IValidateable;

/**
 * <p>
 * value object that represents order
 * </p>
 * 
 * @author Solomka
 *
 */
public final class OrderVO implements IValidateable {

	private final CustomerVO customer;
	private final SellerVO seller;
	private final ShipmentVO shipment;
	private final Collection<OrderItemVO> orderItems;
	private final OrderStatus orderStatus;
	private final DateTime creationDate;
	private final DateTime fulfilmentDate;

	/**
	 * builder that creates immutable instance of {@linkplain OrderVO}
	 * 
	 * @author Solomka
	 *
	 */

	public static class Builder implements IBuilder<OrderVO> {

		private CustomerVO customer;
		private SellerVO seller;
		private ShipmentVO shipment;
		private Collection<OrderItemVO> orderItems;
		private OrderStatus orderStatus;
		private DateTime creationDate;
		private DateTime fulfilmentDate;

		public Builder(final CustomerVO customer, final SellerVO seller, final ShipmentVO shipment,
				final Collection<OrderItemVO> orderItems, final OrderStatus orderStatus, final DateTime creationDate) {

			this.customer = Objects.requireNonNull(customer, "customer must not be null");
			this.seller = Objects.requireNonNull(seller, "seller must not be null");
			this.shipment = Objects.requireNonNull(shipment, "shipment must not be null");
			this.orderItems = Collections
					.unmodifiableCollection(Objects.requireNonNull(orderItems, "orderItems must not be null "));
			this.orderStatus = Objects.requireNonNull(orderStatus, "orderStatus must not be null");
			this.creationDate = Objects.requireNonNull(creationDate, "creationDate must not be null");
		}

		public Builder(@NotNull OrderVO order) {

			if (order == null)
				throw new NullPointerException("order must not be null");

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
			this.orderItems = Collections.unmodifiableCollection(orderItems);
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
			// TODO Auto-generated method stub
			return new OrderVO(this);
		}

	}

	private OrderVO(@NotNull Builder builder) {

		if (builder == null)
			throw new NullPointerException("builder must not be null");

		this.customer = builder.getCustomer();
		this.seller = builder.getSeller();
		this.shipment = builder.getShipment();
		this.orderItems = builder.getOrderItems();
		this.orderStatus = builder.getOrderStatus();
		this.creationDate = builder.getCreationDate();
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
