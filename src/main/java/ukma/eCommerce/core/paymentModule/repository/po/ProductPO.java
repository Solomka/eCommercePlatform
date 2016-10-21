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

import ukma.eCommerce.core.userModule.repository.po.SellerPO;

@Entity
@Table(name = "product")
public class ProductPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5309856094933433559L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "price", column = @Column(name = "price") ),
			@AttributeOverride(name = "currency", column = @Column(name = "currency") ) })
	private Money money;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "description", nullable = false, length = 512)
	private String description;

	@ManyToOne(optional = false)
	@JoinColumn(name = "seller_id", unique = true, nullable = false, updatable = false)
	private SellerPO seller;

	@ManyToOne(optional = false)
	@JoinColumn(name = "type_id", unique = true, nullable = false, updatable = false)
	private TypePO type;

	@OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH)
	private List<OrderItemPO> orderItems;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Money getMoney() {
		return money;
	}

	public void setMoney(Money money) {
		this.money = money;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SellerPO getSeller() {
		return seller;
	}

	public void setSeller(SellerPO seller) {
		this.seller = seller;
	}

	public TypePO getType() {
		return type;
	}

	public void setType(TypePO type) {
		this.type = type;
	}

	public List<OrderItemPO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemPO> orderItems) {
		this.orderItems = orderItems;
	}
	
	

}
