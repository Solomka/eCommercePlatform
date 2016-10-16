package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.userModule.model.domain.vo.SellerVO;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.IValidateable;

/**
 * <p>
 * Value object that represents Product
 * </p>
 * 
 * @author Solomka
 *
 */

public final class ProductVO implements IValidateable {

	private final String name;
	private final BigDecimal price;
	private final int quantity;
	private final String description;
	private final SellerVO seller;
	private final TypeVO type;

	/**
	 * builder that creates immutable instance of {@linkplain ProductVO}
	 * 
	 * @author Solomka
	 *
	 */
	public static class Builder implements IBuilder<ProductVO> {

		private String name;
		private BigDecimal price;
		private int quantity;
		private String description;

		private SellerVO seller;
		private TypeVO type;

		public Builder(@NotNull ProductVO product) {

			if (product == null)
				throw new NullPointerException("product must not be null");

			setName(product.getName()).setPrice(product.getPrice()).setQuantity(product.getQuantity())
					.setDescription(product.getDescription()).setSeller(product.getSeller()).setType(product.getType());

		}

		public Builder(final String name, final BigDecimal price, final int quantity, final String description,
				final SellerVO seller, final TypeVO type) {

			this.name = Objects.requireNonNull(name, "name must not bu null");
			this.price = Objects.requireNonNull(price, "price must not be null");
			this.quantity = Objects.requireNonNull(quantity, "quantity must not be null");
			this.description = Objects.requireNonNull(description, "description must not be null");
			this.seller = Objects.requireNonNull(seller, "seller must not be null");
			this.type = Objects.requireNonNull(type, "type must not be null");

		}

		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public Builder setPrice(BigDecimal price) {
			this.price = price;
			return this;
		}

		public int getQuantity() {
			return quantity;
		}

		public Builder setQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public String getDescription() {
			return description;
		}

		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		public SellerVO getSeller() {
			return seller;
		}

		public Builder setSeller(SellerVO seller) {
			this.seller = seller;
			return this;
		}

		public TypeVO getType() {
			return type;
		}

		public Builder setType(TypeVO type) {
			this.type = type;
			return this;
		}

		@Override
		public ProductVO build() {
			// TODO Auto-generated method stub
			return new ProductVO(this);
		}
	}

	private ProductVO(@NotNull Builder builder) {

		if (builder == null)
			throw new NullPointerException("builder must not be null");
		
		this.name = builder.getName();
		this.price = builder.getPrice();
		this.quantity = builder.getQuantity();
		this.description = builder.getDescription();
		this.seller = builder.getSeller();
		this.type = builder.getType();

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

	public SellerVO getSeller() {
		return seller;
	}

	public TypeVO getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductVO [name=" + name + ", price=" + price + ", quantity=" + quantity + ", description="
				+ description + ", seller=" + seller + ", type=" + type + "]";
	}

}
