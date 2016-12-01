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
	private static final long serialVersionUID = 1L;
	private OrderPO order;
	private ProductPO product;

	public OrderItemID() {
	}

	// @MapsId
	// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne
	public OrderPO getOrder() {
		return order;
	}

	public void setOrder(OrderPO order) {
		this.order = order;
	}

	// @MapsId
	// @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne
	public ProductPO getProduct() {
		return product;
	}

	public void setProduct(ProductPO product) {
		this.product = product;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		OrderItemID that = (OrderItemID) o;

		if (order != null ? !order.equals(that.order) : that.order != null)
			return false;
		if (product != null ? !product.equals(that.product) : that.product != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (order != null ? order.hashCode() : 0);
		result = 31 * result + (product != null ? product.hashCode() : 0);
		return result;
	}
/*
	@Override
	public String toString() {
		return "OrderItemID [order=" + order.toString() + ", product=" + product.toString() + "]";
	}*/

}
