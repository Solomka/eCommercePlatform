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

}
