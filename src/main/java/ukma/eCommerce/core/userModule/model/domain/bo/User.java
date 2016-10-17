package ukma.eCommerce.core.userModule.model.domain.bo;

import ukma.eCommerce.util.IBuilder;

import javax.validation.constraints.NotNull;

/**
 * Base for business objects which describe user
 * 
 * @author Max Oliynick
 */
public class User {

	private final long id;
	private final String name;
	private final String surname;
	private final String email;
	private final String address;
	private final String country;

	/**
	 * Builder which creates instance of {@linkplain User}
	 * 
	 * @author Max Oliynick
	 */
	protected static class Builder implements IBuilder<User> {

		private long id;
		private String name;
		private String surname;
		private String email;
		private String address;
		private String country;

		/**
		 * Copies user's fields
		 */
		public Builder(@NotNull User user) {

			if (user == null)
				throw new NullPointerException("user == null");

			setId(user.getId()).setEmail(user.getEmail()).setName(user.getName()).setSurname(user.getSurname())
					.setAddress(user.getAddress()).setCountry(user.getCountry());
		}

		public Builder(long id, String email) {
			this.id = id;
			this.email = email;
		}

		public long getId() {
			return id;
		}

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public String getName() {
			return name;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public String getSurname() {
			return surname;
		}

		public Builder setSurname(String surname) {
			this.surname = surname;
			return this;
		}

		public String getEmail() {
			return email;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public String getAddress() {
			return address;
		}

		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}

		public String getCountry() {
			return country;
		}

		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}

		@Override
		public User build() {
			return new User(this);
		}

	}

	/**
	 * Constructs instance from builder
	 * 
	 * @param builder
	 *            builder to use
	 */
	protected User(@NotNull Builder builder) {

		if (builder == null)
			throw new NullPointerException("builder == null");

		this.id = builder.getId();
		this.email = builder.getEmail();
		this.name = builder.getName();
		this.surname = builder.getSurname();
		this.address = builder.getAddress();
		this.country = builder.getCountry();
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (!name.equals(user.name)) return false;
		if (!surname.equals(user.surname)) return false;
		if (!email.equals(user.email)) return false;
		if (!address.equals(user.address)) return false;
		return country.equals(user.country);

	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + name.hashCode();
		result = 31 * result + surname.hashCode();
		result = 31 * result + email.hashCode();
		result = 31 * result + address.hashCode();
		result = 31 * result + country.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", address="
				+ address + ", country=" + country + "]";
	}

}
