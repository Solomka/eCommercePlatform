package ukma.eCommerce.core.userModule.model.domain.vo;

import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 10/19/2016.
 */
public final class CustomerID {

	@NotNull
	private final UUID id;

	/**
	 * generate random GUID id
	 */

	/*
	 * public CustomerID() { this.id = UUID.randomUUID(); }
	 */

	// convert from CustomerPO to Customer
	public CustomerID(UUID id) {
		this.id = id;
	}
	
	//convert from CustomerDTO to CustomerEntity
	public CustomerID(String id) {
		this.id = UUID.fromString(id);

	}

	public UUID getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerID other = (CustomerID) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerID [id=" + id + "]";
	}

}
