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

import ukma.eCommerce.core.paymentModule.repository.po.ProductPO;

@Entity
@Table(name = "seller")
public class SellerPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 459735531778726642L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "business_name", nullable = false, length = 50)
	private String businessName;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "email", column = @Column(name = "email") ),
			@AttributeOverride(name = "phone", column = @Column(name = "phone") ),
			@AttributeOverride(name = "login", column = @Column(name = "login") ),
			@AttributeOverride(name = "password", column = @Column(name = "password") ) })
	private Credentials credentials;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(name = "country") ),
			@AttributeOverride(name = "state", column = @Column(name = "state") ),
			@AttributeOverride(name = "region", column = @Column(name = "region") ),
			@AttributeOverride(name = "city", column = @Column(name = "city") ),
			@AttributeOverride(name = "street", column = @Column(name = "street") ),
			@AttributeOverride(name = "index", column = @Column(name = "indexx") ) })
	private Address address;

	// mappedBy designates the field "seller" in the entity ProductVO that is
	// the owner
	// of the relationships
	@OneToMany(mappedBy = "seller", cascade = CascadeType.REFRESH)
	private List<ProductPO> products;

	public SellerPO() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<ProductPO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductPO> products) {
		this.products = products;
	}

	/**
	 * realisation hashCode and equal by means of getters is reasoned by
	 * Hibernate data loading by means of proxy: * When a proxy is loaded, the
	 * data is not stored in the proxy object, but in the "target" object. The
	 * fields in the proxy object will remain null forever (or any value that
	 * they have been initialized with), since all methods invoked on the proxy
	 * will be delegated to the target object.
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
		result = prime * result + ((getBusinessName() == null) ? 0 : getBusinessName().hashCode());
		result = prime * result + ((getCredentials() == null) ? 0 : getCredentials().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getProducts() == null) ? 0 : getProducts().hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SellerPO)) {
			return false;
		}
		SellerPO other = (SellerPO) obj;
		if (getAddress() == null) {
			if (other.getAddress() != null) {
				return false;
			}
		} else if (!getAddress().equals(other.getAddress())) {
			return false;
		}
		if (getBusinessName() == null) {
			if (other.getBusinessName() != null) {
				return false;
			}
		} else if (!getBusinessName().equals(other.getBusinessName())) {
			return false;
		}
		if (getCredentials() == null) {
			if (other.getCredentials() != null) {
				return false;
			}
		} else if (!getCredentials().equals(other.getCredentials())) {
			return false;
		}
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		if (getProducts() == null) {
			if (other.getProducts() != null) {
				return false;
			}
		} else if (!getProducts().equals(other.getProducts())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SellerPO [id=" + id + ", businessName=" + businessName + ", credentials=" + credentials + ", address="
				+ address + ", products=" + products + "]";
	}

}
