package ukma.eCommerce.core.userModule.model.domain.vo;

import java.util.Objects;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * value object that represents Customer user
 * </p>
 * 
 * @author Solomka
 *
 */
public final class CustomerVO extends UserVO {

	private final FullNameVO fullName;
	private final String login;
	private final String password;
	
	/**
	 * builder that creates immutable instance of {@linkplain CustomerVO}
	 * 
	 * @author Solomka
	 *
	 */

	public static class Builder extends UserVO.Builder {

		private FullNameVO fullName;
		private String login;
		private String password;

		public Builder(final String email, final String phone, final FullNameVO fullName, final String login,
				final String password) {
			super(email, phone);
			// TODO Auto-generated constructor stub

			this.fullName = Objects.requireNonNull(fullName, "fullName must not be null");
			this.login = Objects.requireNonNull(login, "login must not be null");
			this.password = Objects.requireNonNull(password, "password must not be null");

		}

		public Builder(@NotNull CustomerVO customer) {
			super(customer);
			setFullName(customer.getFullName()).setLogin(customer.getLogin()).setPassword(customer.getPassword());

		}

		@Override
		public Builder setEmail(String email) {
			// TODO Auto-generated method stub
			super.setEmail(email);
			return this;
		}

		@Override
		public Builder setPhone(String phone) {
			// TODO Auto-generated method stub
			super.setPhone(phone);
			return this;
		}

		public FullNameVO getFullName() {
			return fullName;
		}

		public Builder setFullName(FullNameVO fullName) {
			this.fullName = fullName;
			return this;
		}

		public String getLogin() {
			return login;
		}

		public Builder setLogin(String login) {
			this.login = login;
			return this;
		}

		public String getPassword() {
			return password;
		}

		public Builder setPassword(String password) {
			this.password = password;
			return this;
		}

		@Override
		public CustomerVO build() {
			return new CustomerVO(this);

		}

	}

	private CustomerVO(@NotNull Builder builder) {
		super(builder);
		this.fullName = builder.getFullName();
		this.login = builder.getLogin();
		this.password = builder.getPassword();
	}

	public FullNameVO getFullName() {
		return fullName;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
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
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
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

	@Override
	public String toString() {
		return "CustomerVO [fullName=" + fullName + ", login=" + login + ", password=" + password + "]";
	}

}
