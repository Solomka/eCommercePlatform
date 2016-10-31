package ukma.eCommerce.core.userModule.repository.po;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	public Address() {
	}

	@Column(name = "country", nullable = false, length = 50)
	private String country;

	@Column(name = "state", length = 50)
	private String state;

	@Column(name = "region", length = 50)
	private String region;

	@Column(name = "city", nullable = false, length = 50)
	private String city;

	@Column(name = "street", nullable = false, length = 50)
	private String street;

	@Column(name = "index", nullable = false, length = 8)
	private String index;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
		result = prime * result + ((getCountry() == null) ? 0 : getCountry().hashCode());
		result = prime * result + ((getIndex() == null) ? 0 : getIndex().hashCode());
		result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
		result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
		result = prime * result + ((getStreet() == null) ? 0 : getStreet().hashCode());
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
		Address other = (Address) obj;
		if (getCity() == null) {
			if (other.getCity() != null)
				return false;
		} else if (!getCity().equals(other.getCity()))
			return false;
		if (getCountry() == null) {
			if (other.getCountry() != null)
				return false;
		} else if (!getCountry().equals(other.getCountry()))
			return false;
		if (getIndex() == null) {
			if (other.getIndex() != null)
				return false;
		} else if (!getIndex().equals(other.getIndex()))
			return false;
		if (getRegion() == null) {
			if (other.getRegion() != null)
				return false;
		} else if (!getRegion().equals(other.getRegion()))
			return false;
		if (getState() == null) {
			if (other.getState() != null)
				return false;
		} else if (!getState().equals(other.getState()))
			return false;
		if (getStreet() == null) {
			if (other.getStreet() != null)
				return false;
		} else if (!getStreet().equals(other.getStreet()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", state=" + state + ", region=" + region + ", city=" + city
				+ ", street=" + street + ", index=" + index + "]";
	}

}
