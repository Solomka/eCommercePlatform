package ukma.eCommerce.core.userModule.model.domain.vo;

import java.util.Objects;

/**
 * <p>
 * value object that represents Seller user
 * </p>
 * 
 * @author Solomka
 *
 */

public final class SellerVO extends UserVO {

	private final String businessName;
	private final String country;
	private final String city;
	private final String street;
	private final String index;

	public SellerVO(String email, String phone, String businessName, String country, String city, String street,
			String index) {
		super(email, phone);
		this.businessName = Objects.requireNonNull(businessName, "businessName == null");
		this.country = Objects.requireNonNull(country, "country == null");
		this.city = Objects.requireNonNull(city, "city == null");
		this.street = Objects.requireNonNull(street, "street == null");
		this.index = Objects.requireNonNull(index, "index == null");
	}

	public String getBusinessName() {
		return businessName;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "SellerVO [businessName=" + businessName + ", country=" + country + ", city=" + city + ", street="
				+ street + ", index=" + index + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		SellerVO other = (SellerVO) obj;
		if (businessName == null) {
			if (other.businessName != null)
				return false;
		} else if (!businessName.equals(other.businessName))
			return false;
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
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

}
