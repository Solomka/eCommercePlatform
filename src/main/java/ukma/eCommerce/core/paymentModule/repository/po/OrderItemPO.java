package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "order_items")
@AssociationOverrides({
		@AssociationOverride(name = "orderItemId.order", joinColumns = @JoinColumn(name = "order_id") ),
		@AssociationOverride(name = "orderItemId.product", joinColumns = @JoinColumn(name = "product_id") ) })
public class OrderItemPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderItemID orderItemId = new OrderItemID();

	//@Column(name = "total_quantity", nullable = false)
	private int quantity;
	
	//@Column(name = "total_sum", nullable = false)
	private BigDecimal totalSum;

	public OrderItemPO() {

	}

	public OrderItemPO(OrderItemID orderItemId) {
		this.orderItemId = orderItemId;
	}

	public OrderItemPO(OrderItemID orderItemId, int quantity, BigDecimal totalSum) {
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.totalSum = totalSum;
	}

	public OrderItemPO(int quantity, BigDecimal totalSum) {
		this.quantity = quantity;
		this.totalSum = totalSum;
	}

	@EmbeddedId
	public OrderItemID getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(OrderItemID orderItemId) {
		this.orderItemId = orderItemId;
	}

	@Transient
	public OrderPO getOrder() {
		return getOrderItemId().getOrder();
	}

	public void setOrder(OrderPO orderPO) {
		getOrderItemId().setOrder(orderPO);
	}

	@Transient
	public ProductPO getProduct() {
		return getOrderItemId().getProduct();
	}

	public void setProduct(ProductPO productPO) {
		getOrderItemId().setProduct(productPO);
	}

	@Column(name = "total_quantity", nullable = false)
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "total_sum", nullable = false)
	public BigDecimal getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOrderItemId() == null) ? 0 : getOrderItemId().hashCode());
		result = prime * result + getQuantity();
		result = prime * result + ((getTotalSum() == null) ? 0 : getTotalSum().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof OrderItemPO)) {
			return false;
		}
		OrderItemPO other = (OrderItemPO) obj;
		if (getOrderItemId() == null) {
			if (other.getOrderItemId() != null) {
				return false;
			}
		} else if (!getOrderItemId().equals(other.getOrderItemId())) {
			return false;
		}
		if (getQuantity() != other.getQuantity()) {
			return false;
		}
		if (getTotalSum() == null) {
			if (other.getTotalSum() != null) {
				return false;
			}
		} else if (!getTotalSum().equals(other.getTotalSum())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "OrderItemPO [orderItemId=" + orderItemId + ", quantity=" + quantity + ", totalSum=" + totalSum + "]";
	}

}
