package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
/*
 * TODO DON'T WORK WITH UUID IDS =( 
 */
@Entity
@Table(name = "order_item")
public class OrderItemID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5792041356423644131L;

	private long orderId;
	private long productId;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
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
		OrderItemID other = (OrderItemID) obj;
		if (orderId != other.orderId)
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}

}
