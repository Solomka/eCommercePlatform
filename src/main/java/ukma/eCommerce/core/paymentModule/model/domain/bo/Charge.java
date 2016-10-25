package ukma.eCommerce.core.paymentModule.model.domain.bo;

import java.util.Objects;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.InvoiceStatus;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * Aggregate root object that represents charge
 *
 * @author Solomka
 */
public final class Charge {

	private final UUID id;
	private final InvoiceID invoice;
	private final CreditCard creditCard;
	private DateTime paymentDate;
	private ChargeStatus status;

	/**
	 * builder that creates immutable instance of {@linkplain Charge}
	 *
	 * @author Solomka
	 */

	public static class Builder implements IBuilder<Charge> {

		private UUID id;
		private InvoiceID invoice;
		private CreditCard creditCard;
		private ChargeStatus status;

		public Builder() {
		}

		public Builder(Charge charge) {

			Objects.requireNonNull(charge, "charge must not be null");

			setId(charge.getId()).setInvoice(charge.getInvoice()).setCreditCard(charge.getCreditCard())
					.setStatus(charge.getStatus());
		}

		public UUID getId() {
			return id;
		}

		public Builder setId(UUID id) {
			this.id = id;
			return this;
		}

		/*
		 * public Build setID(){ this.id = UUID.randomUUID() return this; }
		 */

		public InvoiceID getInvoice() {
			return invoice;
		}

		public Builder setInvoice(InvoiceID invoice) {
			this.invoice = invoice;
			return this;
		}

		public CreditCard getCreditCard() {
			return creditCard;
		}

		public Builder setCreditCard(CreditCard creditCard) {
			this.creditCard = creditCard;
			return this;
		}

		public ChargeStatus getStatus() {
			return status;
		}

		public Builder setStatus(ChargeStatus status) {
			this.status = status;
			return this;
		}

		@Override
		public Charge build() {
			return new Charge(this);
		}

	}

	private Charge(@NotNull Builder builder) {

		Objects.requireNonNull(builder, "builder must no be null");

		this.id = ValidationUtil.validate(builder.getId());
		this.invoice = ValidationUtil.validate(builder.getInvoice());
		this.creditCard = ValidationUtil.validate(builder.getCreditCard());
		this.status = Objects.requireNonNull(builder.getStatus(), "status == null");
	}

	public UUID getId() {
		return id;
	}

	public InvoiceID getInvoice() {
		return invoice;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public ChargeStatus getStatus() {
		return status;
	}
	
	

	public void declineInvoice() {

		if (status != InvoiceStatus.PENDING || paymentDate != null)
			throw new IllegalStateException("cannot decline non-pending invoice, or payment date was already set");

		this.status = InvoiceStatus.NOT_PAID;
	}

	public void payInvoice(DateTime date) {

		if (status != InvoiceStatus.PENDING || paymentDate != null)
			throw new IllegalStateException("cannot pay for non-pending invoice, or payment date was already set");

		if (creationDate.compareTo(Objects.requireNonNull(date, "date == null")) > 0)
			throw new IllegalArgumentException("payment date cannot be earlier than creation date");

		this.status = InvoiceStatus.PAID;
		this.paymentDate = date;
	}
	
	private static InvoiceStatus checkStatus(DateTime paymentDate, InvoiceStatus status) {

		Objects.requireNonNull(status);

		if (paymentDate != null && (status == InvoiceStatus.NOT_PAID || status == InvoiceStatus.PENDING))
			throw new IllegalArgumentException(String.format("cannot apply payment date in case status is %s", status));

		if (paymentDate == null && status == InvoiceStatus.PAID)
			throw new IllegalArgumentException(
					String.format("either payment date shouldn't be set or status shouldn't be %s", status));

		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Charge other = (Charge) obj;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Charge [id=" + id + ", invoice=" + invoice + ", creditCard=" + creditCard + ", status=" + status + "]";
	}

}
