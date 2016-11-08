package ukma.eCommerce.core.userModule.model.domain.vo;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Created by Максим on 10/19/2016.
 */
public final class SellerID {

	@NotNull
	private final UUID id;

	/**
	 * generate random GUID id
	 */

	public SellerID(String uuid) {
		this.id = UUID.fromString(uuid);
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
		SellerID other = (SellerID) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SellerID [id=" + id + "]";
	}

}
