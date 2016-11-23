package ukma.eCommerce.core.userModule.repository.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Credentials {

	@Column(name = "email", nullable = false, length = 255)
	private String email;

	@Column(name = "phone", nullable = false, length = 16)
	private String phone;

	@Column(name = "login", nullable = false, length = 15)
	private String login;

	@Column(name = "password", nullable = false, length = 128)
	private String password;

	public Credentials() {

	}

	public Credentials(String email, String phone, String login, String password) {
		this.email = email;
		this.phone = phone;
		this.login = login;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getLogin() == null) ? 0 : getLogin().hashCode());
		result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
		result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
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
		Credentials other = (Credentials) obj;
		if (getEmail() == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!getEmail().equals(other.getEmail()))
			return false;
		if (getLogin() == null) {
			if (other.getLogin() != null)
				return false;
		} else if (!getLogin().equals(other.getLogin()))
			return false;
		if (getPassword() == null) {
			if (other.getPassword() != null)
				return false;
		} else if (!getPassword().equals(other.getPassword()))
			return false;
		if (getPhone() == null) {
			if (other.getPhone() != null)
				return false;
		} else if (!getPhone().equals(other.getPhone()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Credentials [email=" + email + ", phone=" + phone + ", login=" + login + ", password=" + password + "]";
	}

}
