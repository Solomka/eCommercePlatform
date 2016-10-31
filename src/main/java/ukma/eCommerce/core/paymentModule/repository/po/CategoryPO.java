package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "category")
public class CategoryPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -164277869240645078L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH)
	private Collection<TypePO> types;

	public CategoryPO() {

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

	public Collection<TypePO> getTypes() {
		return types;
	}

	public void setTypes(Collection<TypePO> types) {
		this.types = types;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getTypes() == null) ? 0 : getTypes().hashCode());
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
		if (!(obj instanceof CategoryPO)) {
			return false;
		}
		CategoryPO other = (CategoryPO) obj;
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
		if (getTypes() == null) {
			if (other.getTypes() != null) {
				return false;
			}
		} else if (!getTypes().equals(other.getTypes())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "CategoryPO [id=" + id + ", name=" + name + ", types=" + types + "]";
	}

}
