package ukma.eCommerce.core.paymentModule.repository.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "creation_date", nullable = false)
	private DateTime creationDate;

	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
	private ChargeStatus status;

	@OneToOne(optional = false)
	@JoinColumn(name = "invoice_id", unique = true, nullable = false, updatable = false)
	private InvoicePO invoice;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
