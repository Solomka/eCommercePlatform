package ukma.eCommerce.core.paymentModule.model.dwo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.util.IBuilder;
import ukma.eCommerce.util.validation.ValidationUtil;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Charge object to persist
 *
 * @author Solomka
 */
public final class ChargeSaveDTO {

	private final InvoiceID invoice;
	private final DateTime paymentDate;
	private final ChargeStatus status;

	/**
	 * builder that creates immutable instance of {@linkplain ChargeSaveDTO}
	 *
	 * @author Solomka
	 */

	public static class Builder implements IBuilder<ChargeSaveDTO> {

		private InvoiceID invoice;
		private DateTime paymentDate;
		private ChargeStatus status;

		public Builder() {
		}

		public Builder(ChargeSaveDTO charge) {

			Objects.requireNonNull(charge, "charge entity must not be null");

			setInvoice(charge.getInvoice()).setStatus(charge.getStatus());
		}

		public InvoiceID getInvoice() {
			return invoice;
		}

		public Builder setInvoice(InvoiceID invoice) {
			this.invoice = invoice;
			return this;
		}

		public DateTime getPaymentDate() {
			return paymentDate;
		}

		public Builder setPaymentDate(DateTime paymentDate) {
			this.paymentDate = paymentDate;
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
		public ChargeSaveDTO build() {
			return new ChargeSaveDTO(this);
		}

	}

	private ChargeSaveDTO(@NotNull Builder builder) {

		Objects.requireNonNull(builder, "builder must no be null");

		this.invoice = ValidationUtil.validate(builder.getInvoice());
		this.paymentDate = builder.getPaymentDate();
		this.status = Objects.requireNonNull(builder.getStatus(), "status == null");
	}

	public DateTime getPaymentDate() {
		return paymentDate;
	}

	public ChargeStatus getStatus() {
		return status;
	}

	public InvoiceID getInvoice() {
		return invoice;
	}

	@Override
	public String toString() {
		return "ChargeEntity [invoice=" + invoice + ", paymentDate=" + paymentDate + ", status=" + status + "]";
	}

}
