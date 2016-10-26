package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.util.UUID;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 10/20/2016.
 */
public final class InvoiceID {

	@NotNull
	private final UUID id;

	/**
	 * generate random GUID id
	 */
	public InvoiceID() {
		this.id = UUID.randomUUID();
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
		InvoiceID other = (InvoiceID) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvoiceID [id=" + id + "]";
	}

}