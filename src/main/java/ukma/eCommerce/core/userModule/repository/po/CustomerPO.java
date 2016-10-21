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
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

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
	
	public CustomerPO(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public FullName getFullname() {
		return fullName;
	}

	public void setFullname(FullName fullName) {
		this.fullName = fullName;
	}

}
