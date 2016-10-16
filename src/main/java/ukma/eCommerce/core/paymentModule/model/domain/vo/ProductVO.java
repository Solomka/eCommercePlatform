package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * <p>
 * Value object that represents Product
 * </p>
 * 
 * @author Solomka
 *
 */

public final class ProductVO {

	private final String name;
	private final BigDecimal price;
	private final int quantity;
	private final String description;

	public ProductVO(String name, BigDecimal price, int quantity, String description) {
		this.name = Objects.requireNonNull(name, "name == null");
		this.price = Objects.requireNonNull(price, "price == null");
		this.quantity = Objects.requireNonNull(quantity, "quntity == null");
		this.description = Objects.requireNonNull(description, "description == null");
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + ", quantity=" + quantity + ", description="
				+ description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + quantity;
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
		ProductVO other = (ProductVO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

}
