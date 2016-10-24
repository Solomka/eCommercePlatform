package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ukma.eCommerce.core.userModule.repository.po.Address;
import ukma.eCommerce.core.userModule.repository.po.CustomerPO;
import ukma.eCommerce.core.userModule.repository.po.FullName;

@Entity
@Table(name = "address")
public class AddressPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4387307304438823005L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "country", column = @Column(name = "country") ),
			@AttributeOverride(name = "state", column = @Column(name = "state") ),
			@AttributeOverride(name = "region", column = @Column(name = "region") ),
			@AttributeOverride(name = "city", column = @Column(name = "city") ),
			@AttributeOverride(name = "street", column = @Column(name = "street") ),
			@AttributeOverride(name = "index", column = @Column(name = "index") ) })
	private Address address;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "firstName", column = @Column(name = "first_name") ),
			@AttributeOverride(name = "lastName", column = @Column(name = "last_name") )

	})
	private FullName fullName;

	@JoinColumn(name = "phone", unique = true, nullable = false, updatable = false)
	private String phone;

	@ManyToOne(optional = false)
	@JoinColumn(name = "customer_id", unique = true, nullable = false, updatable = false)
	private CustomerPO customer;

	@OneToMany(mappedBy = "address", cascade = CascadeType.REFRESH)
	private List<ShipmentPO> shipments;

	public AddressPO() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
