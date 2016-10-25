package ukma.eCommerce.core.paymentModule.model.domain.bo;

import java.util.Objects;

import javax.validation.constraints.Min;

import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProdCharacteritics;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductID;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * Product aggregate root
 *
 * @author Solomka
 */

public final class Product {

	private final ProductID id;
	private final SellerID seller;
	private Price price;
	@Min(1)
	private int availableQuantity;
	private ProdCharacteritics characteristics;

	/**
	 * builder that creates immutable instance of {@linkplain Product}
	 *
	 * @author Solomka
	 */
	public static class Builder implements IBuilder<Product> {

		private ProductID id;
		private SellerID seller;
		private Price price;
		private int availableQuantity;
		private ProdCharacteritics characteristics;

		public Builder() {

		}

		public Builder(Product product) {

			Objects.requireNonNull(product, "product == null");

			setId(product.getId()).setSeller(product.getSeller()).setPrice(product.getPrice())
					.setAvailableQuantity(product.getAvailableQuantity())
					.setCharacteristics(product.getCharacteristics());

		}

		public ProductID getId() {
			return id;
		}

		public Builder setId(ProductID id) {
			this.id = id;
			return this;
		}

		public SellerID getSeller() {
			return seller;
		}

		public Builder setSeller(SellerID seller) {
			this.seller = seller;
			return this;
		}

		public Price getPrice() {
			return price;
		}

		public Builder setPrice(Price price) {
			this.price = price;
			return this;
		}

		public int getAvailableQuantity() {
			return availableQuantity;
		}

		public Builder setAvailableQuantity(int availableQuantity) {
			this.availableQuantity = availableQuantity;
			return this;
		}

		public ProdCharacteritics getCharacteristics() {
			return characteristics;
		}

		public Builder setCharacteristics(ProdCharacteritics characteristics) {
			this.characteristics = characteristics;
			return this;
		}

		@Override
		public Product build() {
			return new Product(this);
		}
	}

	private Product(Builder builder) {
		Objects.requireNonNull(builder, "builder == null");

		this.id = ValidationUtil.validate(builder.getId());
		this.seller = ValidationUtil.validate(builder.getSeller());
		this.price = ValidationUtil.validate(builder.getPrice());
		this.availableQuantity = Objects.requireNonNull(builder.getAvailableQuantity(),
				"availableQuantity must not be null");
		this.characteristics = ValidationUtil.validate(builder.getCharacteristics());
	}

	public Price getPrice() {
		return price;
	}

	public void changePrice(Price price) {
		if (!ValidationUtil.isValid(price)) {
			throw new IllegalArgumentException("price is not valid");
		}
		this.price = price;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void changeAvailableQuantity(int availableQuantity) {
		Objects.requireNonNull(availableQuantity, "availableQuantity must not be null");
		this.availableQuantity = availableQuantity;
	}

	public ProdCharacteritics getCharacteristics() {
		return characteristics;
	}

	public void changeCharacteristics(ProdCharacteritics characteristics) {

		if (!ValidationUtil.isValid(characteristics)) {
			throw new IllegalArgumentException("characteristics are not valid");
		}

		this.characteristics = characteristics;
	}

	public ProductID getId() {
		return id;
	}

	public SellerID getSeller() {
		return seller;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + availableQuantity;
		result = prime * result + ((characteristics == null) ? 0 : characteristics.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
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
		Product other = (Product) obj;
		if (availableQuantity != other.availableQuantity)
			return false;
		if (characteristics == null) {
			if (other.characteristics != null)
				return false;
		} else if (!characteristics.equals(other.characteristics))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", seller=" + seller + ", price=" + price + ", availableQuantity="
				+ availableQuantity + ", characteristics=" + characteristics + "]";
	}

}
