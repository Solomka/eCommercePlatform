package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Customer;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Seller;
import ukma.eCommerce.util.validation.IValidateable;

import java.math.BigDecimal;
import java.util.List;

public final class InvoiceVO implements IValidateable {
	private final Seller seller;
	private final Customer customer;
	private final OrderVO order;
	private final List<OrderItemVO> orderItems;
	private final int quantityTotal;
	private final BigDecimal sumTotal;
	private final ChargeStatus status;
	private final DateTime creationDate;
	public InvoiceVO(Seller seller, Customer customer, OrderVO order, List<OrderItemVO> orderItems, int quantityTotal,
					 BigDecimal sumTotal, ChargeStatus status, DateTime creationDate) {
		super();
		this.seller = seller;
		this.customer = customer;
		this.order = order;
		this.orderItems = orderItems;
		this.quantityTotal = quantityTotal;
		this.sumTotal = sumTotal;
		this.status = status;
		this.creationDate = creationDate;
	}
	public Seller getSeller() {
		return seller;
	}
	public Customer getCustomer() {
		return customer;
	}
	public OrderVO getOrder() {
		return order;
	}
	public List<OrderItemVO> getOrderItems() {
		return orderItems;
	}
	public int getQuantityTotal() {
		return quantityTotal;
	}
	public BigDecimal getSumTotal() {
		return sumTotal;
	}
	public ChargeStatus getStatus() {
		return status;
	}
	public DateTime getCreationDate() {
		return creationDate;
	}
	@Override
	public String toString() {
		return "InvoiceVO [seller=" + seller + ", customer=" + customer + ", order=" + order + ", orderItems="
				+ orderItems + ", quantityTotal=" + quantityTotal + ", sumTotal=" + sumTotal + ", status=" + status
				+ ", creationDate=" + creationDate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + quantityTotal;
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		InvoiceVO other = (InvoiceVO) obj;
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
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (quantityTotal != other.quantityTotal)
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (status != other.status)
			return false;
		if (sumTotal == null) {
			if (other.sumTotal != null)
				return false;
		} else if (!sumTotal.equals(other.sumTotal))
			return false;
		return true;
	}

	
}
