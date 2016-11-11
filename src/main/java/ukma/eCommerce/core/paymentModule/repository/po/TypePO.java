package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "type")
public class TypePO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5011432706124061350L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private CategoryPO category;

	@OneToMany(mappedBy = "type", cascade = CascadeType.REFRESH)
	private List<ProductPO> products;

	public TypePO() {

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

	public CategoryPO getCategory() {
		return category;
	}

	public void setCategory(CategoryPO category) {
		this.category = category;
	}

	public List<ProductPO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductPO> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getProducts() == null) ? 0 : getProducts().hashCode());
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
		if (!(obj instanceof TypePO)) {
			return false;
		}
		TypePO other = (TypePO) obj;
		if (getCategory() == null) {
			if (other.getCategory() != null) {
				return false;
			}
		} else if (!getCategory().equals(other.getCategory())) {
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
		return "TypePO [id=" + id + ", name=" + name + ", category=" + category + ", products=" + products + "]";
	}

}
