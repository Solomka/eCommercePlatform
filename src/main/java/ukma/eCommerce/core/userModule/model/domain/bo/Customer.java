package ukma.eCommerce.core.userModule.model.domain.bo;

public final class Customer extends User {

	private final int bonus;

	/**
	 * Builder which creates instance of {@linkplain Seller}
	 * 
	 * @author ������
	 */
	public static class Builder extends User.Builder {

		private int bonuses;

		public Builder(String id, String email) {
			super(id, email);
		}

		public Builder(Customer user) {
			super(user);
			setBonuses(user.getBonus());
		}

		public int getBonuses() {
			return bonuses;
		}

		public Builder setBonuses(int bonuses) {
			this.bonuses = bonuses;
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
	public Customer(Builder builder) {
		super(builder);
		this.bonus = builder.getBonuses();
	}

	public int getBonus() {
		return bonus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + bonus;
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
		Customer other = (Customer) obj;
		if (bonus != other.bonus)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [bonus=" + bonus + ", toString()=" + super.toString() + "]";
	}

}
