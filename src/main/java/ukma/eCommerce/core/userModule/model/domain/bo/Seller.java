package ukma.eCommerce.core.userModule.model.domain.bo;

import java.util.Objects;

import ukma.eCommerce.core.userModule.model.domain.vo.Address;
import ukma.eCommerce.core.userModule.model.domain.vo.Credentials;
import ukma.eCommerce.core.userModule.model.domain.vo.SellerID;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * Seller aggregate root
 */
public final class Seller {

	private final SellerID id;
	private Credentials credentials;
	private Address address;
	private String businessName;

	/**
	 * Builder which creates instance of {@linkplain Seller}
	 *
	 * @author Max Oliynick
	 */
	public static class Builder implements IBuilder<Seller> {

		private SellerID id;
		private Credentials credentials;
		private Address address;
		private String businessName;

		public Builder() {
		}

		public Builder(Seller user) {

			Objects.requireNonNull(user, "user == null");

			setId(user.getId()).setCredentials(user.getCredentials()).setAddress(user.getAddress())
					.setBusinessName(user.getBusinessName());
		}

		public SellerID getId() {
			return id;
		}

		public Builder setId(SellerID id) {
			this.id = id;
			return this;
		}

		public Credentials getCredentials() {
			return credentials;
		}

		public Builder setCredentials(Credentials credentials) {
			this.credentials = credentials;
			return this;
		}

		public Address getAddress() {
			return address;
		}

		public Builder setAddress(Address address) {
			this.address = address;
			return this;
		}

		public String getBusinessName() {
			return businessName;
		}

		public Builder setBusinessName(String businessName) {
			this.businessName = businessName;
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
	private Seller(Builder builder) {

		Objects.requireNonNull(builder, "builder == null");

		Objects.requireNonNull(builder.getBusinessName(), "business name == null");

		final int bNameLen = builder.getBusinessName().length();

		if (bNameLen < 3 || bNameLen > 50)
			throw new IllegalArgumentException(
					String.format("business name length is out of bounds, was %d", bNameLen));

		this.id = ValidationUtil.validate(builder.getId());
		this.businessName = ValidationUtil.validate(builder.getBusinessName());
		this.credentials = ValidationUtil.validate(builder.getCredentials());
		this.address = ValidationUtil.validate(builder.getAddress());
	}

	public SellerID getId() {
		return id;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public Address getAddress() {
		return address;
	}

	public String getBusinessName() {
		return businessName;
	}

	/**
	 * 
	 * @param credentials
	 *            not null
	 */
	public void changeCredentials(Credentials credentials) {

		if (!ValidationUtil.isValid(credentials))
			throw new IllegalArgumentException("credentials are not valid");

		this.credentials = credentials;
	}

	/**
	 * 
	 * @param address
	 *            not null
	 */

	public void changeAddress(Address address) {

		if (!ValidationUtil.isValid(address))
			throw new IllegalArgumentException("address is not valid");

		this.address = address;
	}

	public void changeBusinessName(String businessName) {
		final int bNameLen = businessName.length();

		if (bNameLen < 3 || bNameLen > 50)
			throw new IllegalArgumentException(
					String.format("business name length is out of bounds, was %d", bNameLen));

		this.businessName = businessName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Seller other = (Seller) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (businessName == null) {
			if (other.businessName != null)
				return false;
		} else if (!businessName.equals(other.businessName))
			return false;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [id=" + id + ", credentials=" + credentials + ", address=" + address + ", businessName="
				+ businessName + "]";
	}

}
