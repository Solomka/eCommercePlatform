package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * class that represents the Composite Primary Key for OrderItemPO
 * 
 * @author Solomka
 *
 */
@Embeddable
public class OrderItemID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8877254696330828343L;

	private OrderPO order;
	private ProductPO product;

	public OrderItemID() {

	}

	@ManyToOne
	public OrderPO getOrder() {
		return order;
	}

	public void setOrder(OrderPO order) {
		this.order = order;
	}

	@ManyToOne
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
		result = prime * result + ((getProduct() == null) ? 0 : getProduct().hashCode());
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
		if (!(obj instanceof OrderItemID)) {
			return false;
		}
		OrderItemID other = (OrderItemID) obj;
		if (getOrder() == null) {
			if (other.getOrder() != null) {
				return false;
			}
		} else if (!getOrder().equals(other.getOrder())) {
			return false;
		}
		if (getProduct() == null) {
			if (other.getProduct() != null) {
				return false;
			}
		} else if (!getProduct().equals(other.getProduct())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "OrderItemID [order=" + order + ", product=" + product + "]";
	}

}
