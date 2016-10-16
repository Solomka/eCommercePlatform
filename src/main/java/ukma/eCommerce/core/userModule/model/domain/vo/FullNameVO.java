package ukma.eCommerce.core.userModule.model.domain.vo;

import java.util.Objects;

import ukma.eCommerce.util.validation.IValidateable;

/**
 * value object that represents full name of Seller and recipient
 * 
 * @author Solomka
 *
 */

public final class FullNameVO implements IValidateable {

	private final String firstName;
	private final String lastName;

	public FullNameVO(String firstName, String lastName) {

		this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
		this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		FullNameVO other = (FullNameVO) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "FullNameVO [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
