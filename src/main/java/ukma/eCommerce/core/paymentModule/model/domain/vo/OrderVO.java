package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.OrderStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerVO;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerVO;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * value object that represents order
 * </p>
 * 
 * @author Solomka
 *
 */
public final class OrderVO {

	private final CustomerVO customer;
	private final SellerVO seller;
	private final ShipmentVO shipment;
	private final List<OrderItemVO> orderItems;
	private final OrderStatus orderStatus;
	private final DateTime creationDate;
	private final DateTime fulfilmentDate;

	public OrderVO(CustomerVO customer, SellerVO seller, ShipmentVO shipment, List<OrderItemVO> orderItems,
			OrderStatus orderStatus, DateTime creationDate, DateTime fulfilmentDate) {

		this.customer = Objects.requireNonNull(customer, "customer == null");
		this.seller = Objects.requireNonNull(seller, "seller == null");
		this.shipment = Objects.requireNonNull(shipment, "shipment == null");
		this.orderItems = Objects.requireNonNull(orderItems, "orderItems == null");
		this.orderStatus = Objects.requireNonNull(orderStatus, "orderStatus == null");
		this.creationDate = Objects.requireNonNull(creationDate, "creationDate == null");
		this.fulfilmentDate = fulfilmentDate;
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

	public List<OrderItemVO> getOrderItems() {
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
	public String toString() {
		return "OrderVO [customer=" + customer + ", seller=" + seller + ", shipment=" + shipment + ", orderItems="
				+ orderItems + ", orderStatus=" + orderStatus + ", creationDate=" + creationDate + ", fulfilmentDate="
				+ fulfilmentDate + "]";
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

}
