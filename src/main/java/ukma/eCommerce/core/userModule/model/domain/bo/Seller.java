package ukma.eCommerce.core.userModule.model.domain.bo;

import java.util.Objects;

public final class Seller extends User {

	private final String businessName;
	private final String country;
	private final String city;
	private final String street;
	private final String index;

	/**
	 * Builder which creates instance of {@linkplain Seller}
	 * 
	 * @author Max Oliynick
	 */
	public static class Builder extends User.Builder {

		private String businessName;
		private String city;
		private String street;
		private String index;

		public Builder(long id, String email) {
			super(id, email);
		}

		public Builder(Seller user) {
			super(user);
		}

		public String getBusinessName() {
			return businessName;
		}

		public Builder setBusinessName(String businessName) {
			this.businessName = businessName;
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
		public Builder setId(long id) {
			super.setId(id);
			return this;
		}

		@Override
		public Builder setName(String name) {
			super.setName(name);
			return this;
		}

		@Override
		public Builder setSurname(String surname) {
			super.setSurname(surname);
			return this;
		}

		@Override
		public Builder setEmail(String email) {
			super.setEmail(email);
			return this;
		}

		@Override
		public Builder setAddress(String address) {
			super.setAddress(address);
			return this;
		}

		@Override
		public Builder setCountry(String country) {
			super.setCountry(country);
			return this;
		}

		@Override
		public Seller build() {
			return new Seller(this);
		}

	}

	/**
	 * Constructs instance from builder
	 * 
	 * @param builder
	 *            builder to use
	 */
	public Seller(Builder builder) {
		super(builder);

		this.businessName = Objects.requireNonNull(builder.getBusinessName());
		this.country = Objects.requireNonNull(builder.getCountry());
		this.city = Objects.requireNonNull(builder.getCity());
		this.street = Objects.requireNonNull(builder.getStreet());
		this.index = Objects.requireNonNull(builder.getIndex());
	}

	public String getBusinessName() {
		return businessName;
	}

	@Override
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Seller seller = (Seller) o;

		if (!businessName.equals(seller.businessName)) return false;
		if (!country.equals(seller.country)) return false;
		if (!city.equals(seller.city)) return false;
		if (!street.equals(seller.street)) return false;
		return index.equals(seller.index);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + businessName.hashCode();
		result = 31 * result + country.hashCode();
		result = 31 * result + city.hashCode();
		result = 31 * result + street.hashCode();
		result = 31 * result + index.hashCode();
		return result;
	}
}
