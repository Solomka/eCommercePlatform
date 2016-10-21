package ukma.eCommerce.core.userModule.repository.po;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ukma.eCommerce.core.paymentModule.repository.po.ProductPO;

@Entity
@Table(name = "seller")
public class SellerPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 459735531778726642L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

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
			@AttributeOverride(name = "index", column = @Column(name = "index") ) })
	private Address address;

	@OneToMany(mappedBy = "seller", cascade = CascadeType.REFRESH)
	private List<ProductPO> products;
	
	public SellerPO() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
