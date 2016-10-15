package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.util.Objects;

import ukma.eCommerce.util.validation.IValidateable;

/**
 * <p>
 * value object that represents Address for order shipment
 * </p>
 * 
 * @author Solomka
 *
 */

public final class AddressVO implements IValidateable {

	private final String country;
	private final String state;
	private final String region;
	private final String city;
	private final String street;
	private final String postCode;

	private final String firstName;
	private final String lastName;
	private final String phone;

	public AddressVO(String country, String state, String region, String city, String street, String postCode,
			String firstName, String lastName, String phone) {

		this.country = Objects.requireNonNull(country, "country == null");
		this.state = state;
		this.region = region;
		this.city = Objects.requireNonNull(city, "city == null");
		this.street = Objects.requireNonNull(street, "street == null");
		this.postCode = Objects.requireNonNull(postCode, "postCode == null ");
		this.firstName = Objects.requireNonNull(firstName, "firstName == null");
		this.lastName = Objects.requireNonNull(lastName, "lastName == null");
		this.phone = Objects.requireNonNull(phone, "phone == null");
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getRegion() {
		return region;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "AddressVO [country=" + country + ", state=" + state + ", region=" + region + ", city=" + city
				+ ", street=" + street + ", postCode=" + postCode + ", firstName=" + firstName + ", lastName="
				+ lastName + ", phone=" + phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		AddressVO other = (AddressVO) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
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
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

}
