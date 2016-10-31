package ukma.eCommerce.core.paymentModule.repository.po;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import ukma.eCommerce.core.userModule.repository.po.SellerPO;

@Entity
@Table(name = "product")
public class ProductPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5309856094933433559L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "price", column = @Column(name = "price") ),
			@AttributeOverride(name = "currency", column = @Column(name = "currency") ) })
	private Price price;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	@Column(name = "description", nullable = false, length = 512)
	private String description;

	/*
	 * The many side of many-to-one bidirectional relationships must not define
	 * the mappedBy element. The many side is always the owning side of the
	 * relationship.
	 */

	@ManyToOne(optional = false)
	@JoinColumn(name = "seller_id", nullable = false)
	private SellerPO seller;

	@ManyToOne(optional = false)
	@JoinColumn(name = "type_id", nullable = false)
	private TypePO type;

	/*
	 * Here is the annotation to add in order to Hibernate to automatically
	 * insert and update ProducItems (if any)
	 * 
	 * @SuppressWarnings("deprecation")
	 * 
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = {
	 * CascadeType.PERSIST, CascadeType.MERGE })
	 * 
	 * @Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	 * org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	 * 
	 */
	@OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH)
	private List<OrderItemPO> orderItems;

	public ProductPO() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Price getMoney() {
		return price;
	}

	public void setMoney(Price money) {
		this.price = money;
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

	/**
	 * TODO: rewrite fields access to getters access for props because of
	 * Hibernate proxy
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (!(obj instanceof ProductPO)) {
			return false;
		}
		ProductPO other = (ProductPO) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (orderItems == null) {
			if (other.orderItems != null) {
				return false;
			}
		} else if (!orderItems.equals(other.orderItems)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (quantity != other.quantity) {
			return false;
		}
		if (seller == null) {
			if (other.seller != null) {
				return false;
			}
		} else if (!seller.equals(other.seller)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ProductPO [id=" + id + ", name=" + name + ", money=" + price + ", quantity=" + quantity
				+ ", description=" + description + ", seller=" + seller + ", type=" + type + ", orderItems="
				+ orderItems + "]";
	}

}
