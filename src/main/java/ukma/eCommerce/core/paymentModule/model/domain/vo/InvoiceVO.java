package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.InvoiceStatus;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerVO;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerVO;
import ukma.eCommerce.util.validation.IValidateable;

/**
 * <p>
 * value object that represents order's invoice
 * </p>
 * 
 * @author Solomka
 *
 */

public final class InvoiceVO implements IValidateable {

	private final SellerVO seller;
	private final CustomerVO customer;
	private final OrderVO order;
	private final List<InvoiceItemVO> orderItems;
	private final int quantityTotal;
	private final BigDecimal sumTotal;
	private final InvoiceStatus status;
	private final DateTime creationDate;

	public InvoiceVO(SellerVO seller, CustomerVO customer, OrderVO order, List<InvoiceItemVO> orderItems,
			int quantityTotal, BigDecimal sumTotal, InvoiceStatus status, DateTime creationDate) {

		this.seller = Objects.requireNonNull(seller, "seller == null");
		this.customer = Objects.requireNonNull(customer, "customer == null");
		this.order = Objects.requireNonNull(order, "order == null");
		this.orderItems = Objects.requireNonNull(orderItems, "orderItems == null");
		this.quantityTotal = Objects.requireNonNull(quantityTotal, "quantityTotal == null");
		this.sumTotal = Objects.requireNonNull(sumTotal, "sumTotal == null");
		this.status = Objects.requireNonNull(status, "status == null");
		this.creationDate = Objects.requireNonNull(creationDate, "creationDate == null");
	}

	public SellerVO getSeller() {
		return seller;
	}

	public CustomerVO getCustomer() {
		return customer;
	}

	public OrderVO getOrder() {
		return order;
	}

	public List<InvoiceItemVO> getOrderItems() {
		return orderItems;
	}

	public int getQuantityTotal() {
		return quantityTotal;
	}

	public BigDecimal getSumTotal() {
		return sumTotal;
	}

	public InvoiceStatus getStatus() {
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
