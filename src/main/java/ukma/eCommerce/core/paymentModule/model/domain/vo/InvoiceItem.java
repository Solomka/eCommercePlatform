package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * value object that represents invoice item
 * </p>
 *
 * @author Solomka
 */

public final class InvoiceItem {

	@Valid
	@NotNull
	private final ProductID product;
	@Min(1)
	private final int quantity;
	@NotNull
	// money for all items!
	private final Price price;

	public InvoiceItem(ProductID product, int quantity, Price price) {
		this.product = Objects.requireNonNull(product);
		this.quantity = quantity;
		this.price = Objects.requireNonNull(price);
	}

	public ProductID getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public Price getPrice() {
		return price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		InvoiceItem that = (InvoiceItem) o;

		if (quantity != that.quantity)
			return false;
		if (!product.equals(that.product))
			return false;
		return price.equals(that.price);

	}

	@Override
	public int hashCode() {
		int result = product.hashCode();
		result = 31 * result + quantity;
		result = 31 * result + price.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "InvoiceItem{" + "product=" + product + ", quantity=" + quantity + ", money=" + price + '}';
	}
}
