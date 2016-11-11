package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;

@Entity
@Table(name = "payment")
public class PaymentPO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5638210075866310457L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "id", unique = true, nullable = false)
	@Type(type = "uuid-char")
	private UUID id;

	@Column(name = "creation_date", nullable = false)
	private DateTime creationDate;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ChargeStatus status;

	@OneToOne(optional = false)
	@JoinColumn(name = "invoice_id", nullable = false, updatable = false)
	private InvoicePO invoice;

	public PaymentPO() {

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}

	public ChargeStatus getStatus() {
		return status;
	}

	public void setStatus(ChargeStatus status) {
		this.status = status;
	}

	public InvoicePO getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoicePO invoice) {
		this.invoice = invoice;
	}

	/**
	 * rewrite fields access to getters access for props because of Hibernate
	 * proxy
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getInvoice() == null) ? 0 : getInvoice().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
		if (!(obj instanceof PaymentPO)) {
			return false;
		}
		PaymentPO other = (PaymentPO) obj;
		if (getCreationDate() == null) {
			if (other.getCreationDate() != null) {
				return false;
			}
		} else if (!getCreationDate().equals(other.getCreationDate())) {
			return false;
		}
		if (getId() == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!getId().equals(other.getId())) {
			return false;
		}
		if (getInvoice() == null) {
			if (other.getInvoice() != null) {
				return false;
			}
		} else if (!getInvoice().equals(other.getInvoice())) {
			return false;
		}
		if (getStatus() != other.getStatus()) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "PaymentPO [id=" + id + ", creationDate=" + creationDate + ", status=" + status + ", invoice=" + invoice
				+ "]";
	}

}
