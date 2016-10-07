package ukma.eCommerce.controller.vo;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;

import ukma.eCommerce.controller.vo.types.OrderStatus;
import ukma.eCommerce.domain.bo.Customer;
import ukma.eCommerce.domain.bo.Seller;
import ukma.eCommerce.domain.bo.Shipment;
import ukma.eCommerce.util.validation.IValidateable;

public final class OrderVO implements IValidateable {

	private final Customer customer;
	private final Seller seller;
	private final Shipment shipment;
	private final List<OrderItemVO> orderItems;
	private final OrderStatus orderStatus;
	private final BigDecimal sumTotal;
	private final DateTime creationDate;
	public OrderVO(Customer customer, Seller seller, Shipment shipment, List<OrderItemVO> orderItems,
			OrderStatus orderStatus, BigDecimal sumTotal, DateTime creationDate) {
		super();
		this.customer = customer;
		this.seller = seller;
		this.shipment = shipment;
		this.orderItems = orderItems;
		this.orderStatus = orderStatus;
		this.sumTotal = sumTotal;
		this.creationDate = creationDate;
	}
	public Customer getCustomer() {
		return customer;
	}
	public Seller getSeller() {
		return seller;
	}
	public Shipment getShipment() {
		return shipment;
	}
	public List<OrderItemVO> getOrderItems() {
		return orderItems;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public BigDecimal getSumTotal() {
		return sumTotal;
	}
	public DateTime getCreationDate() {
		return creationDate;
	}
	@Override
	public String toString() {
		return "OrderVO [customer=" + customer + ", seller=" + seller + ", shipment=" + shipment + ", orderItems="
				+ orderItems + ", orderStatus=" + orderStatus + ", sumTotal=" + sumTotal + ", creationDate="
				+ creationDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((shipment == null) ? 0 : shipment.hashCode());
		result = prime * result + ((sumTotal == null) ? 0 : sumTotal.hashCode());
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
		if (sumTotal == null) {
			if (other.sumTotal != null)
				return false;
		} else if (!sumTotal.equals(other.sumTotal))
			return false;
		return true;
	}

}
