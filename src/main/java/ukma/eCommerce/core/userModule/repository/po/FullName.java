package ukma.eCommerce.core.userModule.repository.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FullName {

	@Column(name = "first_name", nullable = false, length = 35)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 35)
	private String lastName;

	public FullName() {

	}

	public FullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
		result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FullName)) {
			return false;
		}
		FullName other = (FullName) obj;
		if (getFirstName() == null) {
			if (other.getFirstName() != null) {
				return false;
			}
		} else if (!getFirstName().equals(other.getFirstName())) {
			return false;
		}
		if (getLastName() == null) {
			if (other.getLastName() != null) {
				return false;
			}
		} else if (!getLastName().equals(other.getLastName())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "FullName [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
