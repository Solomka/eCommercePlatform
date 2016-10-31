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

	public Price getPrice() {
		return price;
	}

	public void setMoney(Price price) {
		this.price = price;
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
	 * rewrite fields access to getters access for props because of Hibernate
	 * proxy
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getOrderItems() == null) ? 0 : getOrderItems().hashCode());
		result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
		result = prime * result + getQuantity();
		result = prime * result + ((getSeller() == null) ? 0 : getSeller().hashCode());
		result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
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
		if (getDescription() == null) {
			if (other.getDescription() != null) {
				return false;
			}
		} else if (!getDescription().equals(other.getDescription())) {
			return false;
		}
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		if (getName() == null) {
			if (other.getName() != null) {
				return false;
			}
		} else if (!getName().equals(other.getName())) {
			return false;
		}
		if (getOrderItems() == null) {
			if (other.getOrderItems() != null) {
				return false;
			}
		} else if (!getOrderItems().equals(other.getOrderItems())) {
			return false;
		}
		if (getPrice() == null) {
			if (other.getPrice() != null) {
				return false;
			}
		} else if (!getPrice().equals(other.getPrice())) {
			return false;
		}
		if (getQuantity() != other.getQuantity()) {
			return false;
		}
		if (getSeller() == null) {
			if (other.getSeller() != null) {
				return false;
			}
		} else if (!getSeller().equals(other.getSeller())) {
			return false;
		}
		if (getType() == null) {
			if (other.getType() != null) {
				return false;
			}
		} else if (!getType().equals(other.getType())) {
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
