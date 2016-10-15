package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.util.Objects;

/**
 * <p>
 * value object that represents Customer user
 * </p>
 * 
 * @author Solomka
 *
 */
public final class CustomerVO extends UserVO {

	private final String firstName;
	private final String lastName;
	private final String login;
	private final String password;

	/*
	 * do we want to save credit cards for our registered users by means of
	 * creating STRIPE CUSTOMER in order to prevent their inputting credit card
	 * credentials per each purchase
	 */

	// private final String stripeCustomerID;

	/*
	 * public CustomerVO(String email, String phone, String firstName, String
	 * lastName, String login, String password, String stripeCustomerID) {
	 * super(email, phone); this.firstName = Objects.requireNonNull(firstName,
	 * "firstName == null"); this.lastName = Objects.requireNonNull(lastName,
	 * "lastName == null"); this.login = Objects.requireNonNull(login,
	 * "login == null"); this.password = Objects.requireNonNull(password,
	 * "password == null"); this.stripeCustomerID =
	 * Objects.requireNonNull(stripeCustomerID,
	 * "stripeAstripeCustomerID == null"); }
	 */

	public CustomerVO(String email, String phone, String firstName, String lastName, String login, String password) {
		super(email, phone);
		this.firstName = Objects.requireNonNull(firstName, "firstName == null");
		this.lastName = Objects.requireNonNull(lastName, "lastName == null");
		this.login = Objects.requireNonNull(login, "login == null");
		this.password = Objects.requireNonNull(password, "password == null");
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "CustomerVO [firstName=" + firstName + ", lastName=" + lastName + ", login=" + login + ", password="
				+ password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		CustomerVO other = (CustomerVO) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}
