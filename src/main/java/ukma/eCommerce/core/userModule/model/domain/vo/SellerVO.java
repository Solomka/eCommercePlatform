package ukma.eCommerce.core.userModule.model.domain.vo;

import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * value object that represents Seller user
 * </p>
 * 
 * @author Solomka
 *
 */

public final class SellerVO extends UserVO {

	private final String businessName;
	private final String country;
	private final String city;
	private final String street;
	private final String index;

	/**
	 * builder that creates immutable instance of {@linkplain SellerVO}
	 * 
	 * @author Solomka
	 *
	 */

	public static class Builder extends UserVO.Builder {

		private String businessName;
		private String country;
		private String city;
		private String street;
		private String index;

		public Builder(final String email, final String phone, final String businesName, final String country,
				final String city, final String street, String index) {
			super(email, phone);

			this.businessName = Objects.requireNonNull(businesName, "businesName must not be null");
			this.country = Objects.requireNonNull(country, "country must not be null");
			this.city = Objects.requireNonNull(city, "city must not be null");
			this.street = Objects.requireNonNull(street, "street must not be null");
			this.index = Objects.requireNonNull(index, "index must not be null");
		}

		public Builder(@NotNull SellerVO seller) {
			super(seller);
			setBusinessName(seller.getBusinessName()).setCountry(seller.getCountry()).setCity(seller.getCity())
					.setStreet(seller.getStreet()).setIndex(seller.getIndex());
		}

		@Override
		public Builder setEmail(String email) {
			super.setEmail(email);
			return this;
		}

		@Override
		public Builder setPhone(String phone) {
			super.setPhone(phone);
			return this;
		}

		public String getBusinessName() {
			return businessName;
		}

		public Builder setBusinessName(String businessName) {
			this.businessName = businessName;
			return this;
		}

		public String getCountry() {
			return country;
		}

		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}

		public String getCity() {
			return city;
		}

		public Builder setCity(String city) {
			this.city = city;
			return this;
		}

		public String getStreet() {
			return street;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public String getIndex() {
			return index;
		}

		public Builder setIndex(String index) {
			this.index = index;
			return this;
		}

		@Override
		public UserVO build() {
			return new SellerVO(this);
		}

	}

	private SellerVO(@NotNull Builder builder) {
		super(builder);
		this.businessName = builder.getBusinessName();
		this.country = builder.getCountry();
		this.city = builder.getCity();
		this.street = builder.getStreet();
		this.index = builder.getIndex();

	}

	public String getBusinessName() {
		return businessName;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getIndex() {
		return index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SellerVO other = (SellerVO) obj;
		if (businessName == null) {
			if (other.businessName != null)
				return false;
		} else if (!businessName.equals(other.businessName))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SellerVO [businessName=" + businessName + ", country=" + country + ", city=" + city + ", street="
				+ street + ", index=" + index + "]";
	}

}
