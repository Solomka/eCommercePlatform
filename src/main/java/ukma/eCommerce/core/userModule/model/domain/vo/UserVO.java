package ukma.eCommerce.core.userModule.model.domain.vo;

import java.util.Objects;

/**
 * <p>
 * value object that represents General user
 * </p>
 * 
 * @author Solomka
 *
 */
public class UserVO {

	private final String email;
	private final String phone;

	protected UserVO(String email, String phone) {

		this.email = Objects.requireNonNull(email, "email == null");
		this.phone = Objects.requireNonNull(phone, "phone == null");
	}

	public final String getEmail() {
		return email;
	}

	public final String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "UserVO [email=" + email + ", phone=" + phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		UserVO other = (UserVO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}
