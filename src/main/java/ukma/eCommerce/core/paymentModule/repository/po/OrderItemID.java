package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Embeddable;

import org.hibernate.annotations.Type;

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

	/**
	 * UUID is being mapped as BINARY[1] by Hibernate, but none MySQL Dialects
	 * maps BINARY to a MySQL data type. You can override this behavior by
	 * explicitly specifying uuid-char as the type.
	 */

	@Type(type = "uuid-char")
	private UUID orderId;

	/**
	 * UUID is being mapped as BINARY[1] by Hibernate, but none MySQL Dialects
	 * maps BINARY to a MySQL data type. You can override this behavior by
	 * explicitly specifying uuid-char as the type.
	 */
	@Type(type = "uuid-char")
	private UUID productId;

	// must have a default constructor
	public OrderItemID() {

	}

	public UUID getOrderId() {
		return orderId;
	}

	public void setOrderId(UUID orderId) {
		this.orderId = orderId;
	}

	public UUID getProductID() {
		return productId;
	}

	public void setProductID(UUID productID) {
		this.productId = productID;
	}

	// Must have a hashCode method
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	// Must have an equals method
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemID other = (OrderItemID) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrderItemID [orderId=" + orderId + ", productID=" + productId + "]";
	}

}
