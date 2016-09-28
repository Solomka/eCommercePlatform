package ukma.eCommerce.domain.bo;

public final class Seller extends User {

	private final String company;

	/**
	 * Builder which creates instance of {@linkplain Seller}
	 * 
	 * @author Максим
	 */
	public static class Builder extends User.Builder {

		private String company;

		public Builder(String id, String email) {
			super(id, email);
		}

		public Builder(Seller user) {
			super(user);
			setCompany(user.getCompany());
		}

		public String getCompany() {
			return company;
		}

		public Builder setCompany(String company) {
			this.company = company;
			return this;
		}

		@Override
		public Builder setId(String id) {
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
		this.company = builder.getCompany();
	}

	public String getCompany() {
		return company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((company == null) ? 0 : company.hashCode());
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
		Seller other = (Seller) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seller [company=" + company + ", toString()=" + super.toString() + "]";
	}

}
