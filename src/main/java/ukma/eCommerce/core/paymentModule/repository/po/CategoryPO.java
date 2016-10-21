package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -164277869240645078L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@OneToMany(mappedBy = "category")
	private Collection<TypePO> types;

	public CategoryPO() {

	}

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

	public Collection<TypePO> getTypes() {
		return types;
	}

	public void setTypes(Collection<TypePO> types) {
		this.types = types;
	}

}
