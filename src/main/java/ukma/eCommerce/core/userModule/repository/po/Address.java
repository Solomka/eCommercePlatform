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

}
