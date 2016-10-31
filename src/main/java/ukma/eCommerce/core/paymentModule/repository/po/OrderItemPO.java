package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItemPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemID orderItemId;

	/**
	 * We want to use the same order id for OrderItemPO entity that is being
	 * used in OrderItemID embeddable. Hence annotated with MapsId("orderId")
	 * MapsId annotation says that orderId field from OrderItemID class must
	 * have the value of order_id field which is in its turn OrderPO id.
	 * 
	 * It means that value of orderId field is to be stored as foreign key.
	 */
	@MapsId("orderId")
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false, updatable = false)
	private OrderPO order;

	/**
	 * We want to use the same product id for OrderItemPO entity that is being
	 * used in OrderItemID embeddable. Hence annotated with MapsId("productId")
	 * MapsId annotation says that productId field from OrderItemID class must
	 * have the value of product_id field which is in its turn ProductPO id.
	 * 
	 * It means that value of productId field is to be stored as foreign key.
	 */
	@MapsId("productId")
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false, updatable = false)
	private ProductPO product;

	@Column(name = "total_quantity", nullable = false)
	private int quantity;

	@Column(name = "total_sum", nullable = false)
	private BigDecimal totalSum;

	/*
	 * @Embedded
	 * 
	 * @AttributeOverrides({ @AttributeOverride(name = "price", column
	 * = @Column(name = "total_sum") ),
	 * 
	 * @AttributeOverride(name = "currency", column = @Column(name = "currency")
	 * ) }) private Price price;
	 */

	public OrderItemPO() {

	}

	public OrderItemID getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(OrderItemID orderItemId) {
		this.orderItemId = orderItemId;
	}

	public OrderPO getOrder() {
		return order;
	}

	public void setOrder(OrderPO order) {
		this.order = order;
	}

	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

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
		result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
		result = prime * result + ((getOrderItemId() == null) ? 0 : getOrderItemId().hashCode());
		result = prime * result + ((getProduct() == null) ? 0 : getProduct().hashCode());
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
		if (getOrder() == null) {
			if (other.getOrder() != null) {
				return false;
			}
		} else if (!getOrder().equals(other.getOrder())) {
			return false;
		}
		if (getOrderItemId() == null) {
			if (other.getOrderItemId() != null) {
				return false;
			}
		} else if (!getOrderItemId().equals(other.getOrderItemId())) {
			return false;
		}
		if (getProduct() == null) {
			if (other.getProduct() != null) {
				return false;
			}
		} else if (!getProduct().equals(other.getProduct())) {
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
		return "OrderItemPO [orderItemId=" + orderItemId + ", order=" + order + ", product=" + product + ", quantity="
				+ quantity + ", totalSum=" + totalSum + "]";
	}

}
