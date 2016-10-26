package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.hibernate.validator.constraints.NotEmpty;
import ukma.eCommerce.core.userModule.model.domain.vo.Address;
import ukma.eCommerce.core.userModule.model.domain.vo.FullName;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.Phone;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.util.Objects;

public final class ShipmentDetails {

	private final Address address;
	private final FullName fullName;
	@NotEmpty
	@Phone
	private final String phone;

	public static class Builder implements IBuilder<ShipmentDetails> {

		private Address address;
		private FullName fullName;
		private String phone;

		public Builder() {

		}

		public Builder(ShipmentDetails address) {

			Objects.requireNonNull(address, "address == null");

			setAddress(address.getAddress()).setFullName(address.getFullName()).setPhone(address.getPhone());

		}

		public Address getAddress() {
			return address;
		}

		public Builder setAddress(Address address) {
			this.address = address;
			return this;
		}

		public FullName getFullName() {
			return fullName;
		}

		public Builder setFullName(FullName fullname) {
			this.fullName = fullname;
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
		public ShipmentDetails build() {
			return new ShipmentDetails(this);
		}

	}

	private ShipmentDetails(Builder builder) {
		Objects.requireNonNull(builder, "builder == null");

		this.address = ValidationUtil.validate(builder.getAddress());
		this.fullName = ValidationUtil.validate(builder.getFullName());
		this.phone = ValidationUtil.validate(builder.getPhone());

	}

	public Address getAddress() {
		return address;
	}

	public FullName getFullName() {
		return fullName;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
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
		ShipmentDetails other = (ShipmentDetails) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (fullName == null) {
			if (other.fullName != null)
				return false;
		} else if (!fullName.equals(other.fullName))
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
		return "ShipmentDetails [address=" + address + ", fullName=" + fullName + ", phone=" + phone + "]";
	}

}
