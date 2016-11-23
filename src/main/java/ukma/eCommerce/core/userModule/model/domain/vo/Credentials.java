package ukma.eCommerce.core.userModule.model.domain.vo;

import java.util.Objects;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import ukma.eCommerce.util.validation.Login;
import ukma.eCommerce.util.validation.Password;
import ukma.eCommerce.util.validation.Phone;

/**
 * <p>
 * Value object which represents account credentials
 * </p>
 * Created by Максим on 10/19/2016.
 */
public final class Credentials {

	@NotEmpty
	@Email
	@Max(255)
	private final String email;
	@NotEmpty
	@Phone
	private final String phone;
	@NotEmpty
	@Password
	private final String password;
	@NotEmpty
	@Login
	private final String login;

	/**
	 * Constructs new instance. All parameters are required
	 *
	 * @param email
	 * @param phone
	 * @param password
	 * @param login
	 */
	public Credentials(String email, String phone, String password, String login) {
		this.email = Objects.requireNonNull(email);
		this.phone = Objects.requireNonNull(phone);
		this.password = Objects.requireNonNull(password);
		this.login = Objects.requireNonNull(login);
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getPassword() {
		return password;
	}

	public String getLogin() {
		return login;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Credentials that = (Credentials) o;

		if (!email.equals(that.email))
			return false;
		if (!phone.equals(that.phone))
			return false;
		if (!password.equals(that.password))
			return false;
		return login.equals(that.login);

	}

	@Override
	public int hashCode() {
		int result = email.hashCode();
		result = 31 * result + phone.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + login.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Credentials{" + "email='" + email + '\'' + ", phone='" + phone + '\'' + ", password='" + password + '\''
				+ ", login='" + login + '\'' + '}';
	}
}
