package ukma.eCommerce.core.userModule.model.domain.vo;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import ukma.eCommerce.util.IBuilder;

/**
 * <p>
 * value object that represents User
 * </p>
 * 
 * @author Solomka
 *
 */
public class UserVO {

	private final String email;
	private final String phone;

	public static class Builder implements IBuilder<UserVO> {

		private String email;
		private String phone;

		public Builder() {
		}

		public Builder(final String email, final String phone) {
			this.email = email;
			this.phone = phone;
		}

		public Builder(@NotNull UserVO user) {

			if (user == null)
				throw new NullPointerException("user must not be null");

			setEmail(user.getEmail()).setPhone(user.getPhone());
		}

		public String getEmail() {
			return email;
		}

		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		public String getPhone() {
			return phone;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		@Override
		public UserVO build() {
			return new UserVO(this);
		}

	}

	protected UserVO(@NotNull Builder builder) {
		Objects.requireNonNull(builder, "builder must not be null");
		this.email = Objects.requireNonNull(builder.getEmail(), "email must not be null");
		this.phone = Objects.requireNonNull(builder.getPhone(), "phone must not be null");
	}

	public final String getEmail() {
		return email;
	}

	public final String getPhone() {
		return phone;
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

	@Override
	public String toString() {
		return "UserVO [email=" + email + ", phone=" + phone + "]";
	}

}
