package ukma.eCommerce.core.userModule.repository.po;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import ukma.eCommerce.core.paymentModule.repository.po.AddressPO;
import ukma.eCommerce.core.paymentModule.repository.po.OrderPO;

@Entity
@Table(name = "customer")
public class CustomerPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6989120513965832589L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "email", column = @Column(name = "email") ),
			@AttributeOverride(name = "phone", column = @Column(name = "phone") ),
			@AttributeOverride(name = "login", column = @Column(name = "login") ),
			@AttributeOverride(name = "password", column = @Column(name = "password") ) })
	private Credentials credentials;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "firstName", column = @Column(name = "first_name") ),
			@AttributeOverride(name = "lastName", column = @Column(name = "last_name") )

	})
	private FullName fullName;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REFRESH)
	private List<OrderPO> orders;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REFRESH)
	private List<AddressPO> addresses;

	public CustomerPO() {

	}

	public CustomerPO(UUID id) {
		this.id = id;
	}

	public CustomerPO(UUID id, Credentials credentials, FullName fullName) {
		this.id = id;
		this.credentials = credentials;
		this.fullName = fullName;
	}

	public CustomerPO(Credentials credentials, FullName fullName) {
		this.credentials = credentials;
		this.fullName = fullName;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public List<OrderPO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderPO> orders) {
		this.orders = orders;
	}

	public List<AddressPO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressPO> addresses) {
		this.addresses = addresses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAddresses() == null) ? 0 : getAddresses().hashCode());
		result = prime * result + ((getCredentials() == null) ? 0 : getCredentials().hashCode());
		result = prime * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getOrders() == null) ? 0 : getOrders().hashCode());
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
		if (!(obj instanceof CustomerPO)) {
			return false;
		}
		CustomerPO other = (CustomerPO) obj;
		if (getAddresses() == null) {
			if (other.getAddresses() != null) {
				return false;
			}
		} else if (!getAddresses().equals(other.getAddresses())) {
			return false;
		}
		if (getCredentials() == null) {
			if (other.getCredentials() != null) {
				return false;
			}
		} else if (!getCredentials().equals(other.getCredentials())) {
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
		if (getOrders() == null) {
			if (other.getOrders() != null) {
				return false;
			}
		} else if (!getOrders().equals(other.getOrders())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CustomerPO [id=" + id + ", credentials=" + credentials + ", fullName=" + fullName + ", orders=" + orders
				+ ", addresses=" + addresses + "]";
	}

}
