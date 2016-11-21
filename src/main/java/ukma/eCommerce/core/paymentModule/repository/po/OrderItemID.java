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
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		if (order == null) {
			if (other.order != null) {
				return false;
			}
		} else if (!order.equals(other.order)) {
			return false;
		}
		if (product == null) {
			if (other.product != null) {
				return false;
			}
		} else if (!product.equals(other.product)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "OrderItemID [order=" + order + ", product=" + product + "]";
	}

}
