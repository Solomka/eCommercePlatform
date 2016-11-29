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
@Table(name = "order_item")
@AssociationOverrides({ @AssociationOverride(name = "orderItemId.order", joinColumns = @JoinColumn(name = "order_id") ),
		@AssociationOverride(name = "orderItemId.product", joinColumns = @JoinColumn(name = "product_id") ) })
public class OrderItemPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OrderItemID orderItemId = new OrderItemID();

	// @Column(name = "total_quantity", nullable = false)
	private int quantity;

	// @Column(name = "total_sum", nullable = false)
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
	private OrderItemID getOrderItemId() {
		return orderItemId;
	}

	private void setOrderItemId(OrderItemID orderItemId) {
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

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		OrderItemPO that = (OrderItemPO) o;

		if (getOrderItemId() != null ? !getOrderItemId().equals(that.getOrderItemId()) : that.getOrderItemId() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getOrderItemId() != null ? getOrderItemId().hashCode() : 0);
	}

	@Override
	public String toString() {
		return "OrderItemPO [orderItemId=" + orderItemId + ", quantity=" + quantity + ", totalSum=" + totalSum + "]";
	}

}
