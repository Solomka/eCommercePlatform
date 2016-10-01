package ukma.eCommerce.domain.bo;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.IValidateable;

/**
<<<<<<< HEAD
 * Base for business objects which describe user
 * 
 * @author ������
 */
public class User implements IValidateable {

	private final String id;
	private final String name;
	private final String surname;
	private final String email;
	private final String address;
	private final String country;

	/**
	 * Builder which creates instance of {@linkplain User}
	 * 
	 * @author ������
	 */
	public static class Builder implements IBuilder<User> {

		private String id;
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

		public Builder(String id, String email) {
			this.id = id;
			this.email = email;
		}

		public String getId() {
			return id;
		}

		public Builder setId(String id) {
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

	public String getId() {
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", address="
				+ address + ", country=" + country + "]";
	}

}
