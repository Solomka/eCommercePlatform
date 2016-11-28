package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import ukma.eCommerce.core.userModule.repository.po.Address;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.core.userModule.repository.po.FullName;
import ukma.eCommerce.util.IBuilder;

/**
 * 
 * @author Solomka
 *
 */
@Entity
@Table(name = "address")
public class AddressPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4387307304438823005L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(name = "country") ),
			@AttributeOverride(name = "state", column = @Column(name = "state") ),
			@AttributeOverride(name = "region", column = @Column(name = "region") ),
			@AttributeOverride(name = "city", column = @Column(name = "city") ),
			@AttributeOverride(name = "street", column = @Column(name = "street") ),
			@AttributeOverride(name = "index", column = @Column(name = "indexx") ) })
	private Address address;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "firstName", column = @Column(name = "first_name") ),
			@AttributeOverride(name = "lastName", column = @Column(name = "last_name") )

	})
	private FullName fullName;

	@Column(name = "phone", nullable = false, length = 16)
	private String phone;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	private CustomerPO customer;

	@OneToMany(mappedBy = "address", cascade = CascadeType.REFRESH)
	private List<ShipmentPO> shipments;

	public static class Builder implements IBuilder<AddressPO> {

		private UUID id;
		private Address address;
		private FullName fullName;
		private String phone;
		private CustomerPO customer;

		public Builder() {

		}

		public Builder(AddressPO addressPO) {
			Objects.requireNonNull(addressPO, "address must not be null");
			setId(addressPO.getId()).setAddress(addressPO.getAddress()).setFullName(addressPO.getFullName())
					.setPhone(addressPO.getPhone()).setCustomer(addressPO.getCustomer());

		}

		public UUID getId() {
			return id;
		}

		public Builder setId(UUID id) {
			this.id = id;
			return this;
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

		public Builder setFullName(FullName fullName) {
			this.fullName = fullName;
			return this;
		}

		public String getPhone() {
			return phone;
		}

		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public CustomerPO getCustomer() {
			return customer;
		}

		public Builder setCustomer(CustomerPO customer) {
			this.customer = customer;
			return this;
		}

		@Override
		public AddressPO build() {
			// TODO Auto-generated method stub
			return new AddressPO(this);
		}

	}

	public AddressPO() {

	}

	public AddressPO(Builder builder) {

		Objects.requireNonNull(builder, "builder must not be null");
		this.id = builder.getId();
		this.address = Objects.requireNonNull(builder.getAddress());
		this.fullName = Objects.requireNonNull(builder.getFullName());
		this.phone = Objects.requireNonNull(builder.getPhone());
		this.customer = Objects.requireNonNull(builder.getCustomer());

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public CustomerPO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerPO customer) {
		this.customer = customer;
	}

	public List<ShipmentPO> getShipments() {
		return shipments;
	}

	public void setShipments(List<ShipmentPO> shipments) {
		this.shipments = shipments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
		result = prime * result + ((getCustomer() == null) ? 0 : getCustomer().hashCode());
		result = prime * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
		result = prime * result + ((getShipments() == null) ? 0 : getShipments().hashCode());
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
		if (!(obj instanceof AddressPO)) {
			return false;
		}
		AddressPO other = (AddressPO) obj;
		if (getAddress() == null) {
			if (other.getAddress() != null) {
				return false;
			}
		} else if (!getAddress().equals(other.getAddress())) {
			return false;
		}
		if (getCustomer() == null) {
			if (other.getCustomer() != null) {
				return false;
			}
		} else if (!getCustomer().equals(other.getCustomer())) {
			return false;
		}
		if (getFullName() == null) {
			if (other.getFullName() != null) {
				return false;
			}
		} else if (!getFullName().equals(other.getFullName())) {
			return false;
		}
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		if (getPhone() == null) {
			if (other.getPhone() != null) {
				return false;
			}
		} else if (!getPhone().equals(other.getPhone())) {
			return false;
		}
		if (getShipments() == null) {
			if (other.getShipments() != null) {
				return false;
			}
		} else if (!getShipments().equals(other.getShipments())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AddressPO [id=" + id + ", address=" + address + ", fullName=" + fullName + ", phone=" + phone
				+ ", customer=" + customer + ", shipments=" + shipments + "]";
	}

}
