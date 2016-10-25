package ukma.eCommerce.core.userModule.model.domain.bo;

import java.util.Objects;

import ukma.eCommerce.core.userModule.model.domain.vo.Credentials;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerID;
import ukma.eCommerce.core.userModule.model.domain.vo.FullName;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * Customer aggregate root
 */

public final class Customer {

	private final CustomerID id;
	private FullName fullName;
	private Credentials credentials;

	/**
	 * Builder which creates instance of {@linkplain Customer}
	 *
	 * @author Max Oliynick
	 */
	public static class Builder implements IBuilder<Customer> {

		private CustomerID id;
		private FullName fullName;
		private Credentials credentials;

		public Builder() {
		}

		public Builder(Customer customer) {
			Objects.requireNonNull(customer, "customer == null");
			setId(customer.getId()).setFullName(customer.getFullName()).setCredentials(customer.getCredentials());
		}

		public CustomerID getId() {
			return id;
		}

		public Builder setId(CustomerID id) {
			this.id = id;
			return this;
		}

		public FullName getFullName() {
			return fullName;
		}

		public Builder setFullName(FullName fullNameVO) {
			this.fullName = fullNameVO;
			return this;
		}

		public Credentials getCredentials() {
			return credentials;
		}

		public Builder setCredentials(Credentials credentials) {
			this.credentials = credentials;
			return this;
		}

		@Override
		public Customer build() {
			return new Customer(this);
		}

	}

	/**
	 * Constructs instance from builder
	 *
	 * @param builder
	 *            builder to use
	 */
	private Customer(Builder builder) {

		Objects.requireNonNull(builder, "builder == null");

		this.id = ValidationUtil.validate(builder.getId());
		this.fullName = ValidationUtil.validate(builder.getFullName());
		this.credentials = ValidationUtil.validate(builder.getCredentials());

	}

	public CustomerID getId() {
		return id;
	}

	public FullName getFullName() {
		return fullName;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	/**
	 * @param credentials
	 *            not null
	 */
	public void changeCredentials(Credentials credentials) {

		if (!ValidationUtil.isValid(credentials))
			throw new IllegalArgumentException("credentials are not valid");

		this.credentials = credentials;
	}

	/**
	 * @param fullName
	 *            not null
	 */
	public void changeFullName(FullName fullName) {
		if(!ValidationUtil.isValid((fullName)))
				throw new IllegalArgumentException("fullName is not valid");

		this.fullName = fullName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credentials == null) ? 0 : credentials.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
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
		Customer other = (Customer) obj;
		if (credentials == null) {
			if (other.credentials != null)
				return false;
		} else if (!credentials.equals(other.credentials))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
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
		return "Customer [id=" + id + ", fullName=" + fullName + ", credentials=" + credentials + "]";
	}

}
