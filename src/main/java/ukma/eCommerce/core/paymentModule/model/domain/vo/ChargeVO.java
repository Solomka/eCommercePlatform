package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;
import java.util.Objects;

import org.joda.time.DateTime;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.util.IBuilder;

/**
 * value object that represents charge
 * 
 * @author Solomka
 *
 */
public final class ChargeVO {

	private final InvoiceVO invoice;
	private final CreditCardVO creditCardVO;
	private final String currency;
	private final BigDecimal sumTotal;
	private final ChargeStatus status;
	private final DateTime creationDate;

	/**
	 * builder that creates immutable instance of {@linkplain ChargeVO}
	 * 
	 * @author Solomka
	 *
	 */

	public static class Builder implements IBuilder<ChargeVO> {

		private InvoiceVO invoice;
		private CreditCardVO creditCardVO;
		private String currency;
		private BigDecimal sumTotal;
		private ChargeStatus status;
		private DateTime creationDate;

		public Builder(final InvoiceVO invoice, final CreditCardVO creditCardVO, final String currency,
				final BigDecimal sumTotal, final ChargeStatus status, final DateTime creationDate) {

			this.invoice = Objects.requireNonNull(invoice, "invoice == null");
			this.creditCardVO = Objects.requireNonNull(creditCardVO, "creditCard == null");
			this.currency = Objects.requireNonNull(currency, "currency == null");
			this.sumTotal = Objects.requireNonNull(sumTotal, "sumTotal == null");
			this.status = Objects.requireNonNull(status, "status == null");
			this.creationDate = Objects.requireNonNull(creationDate, "creationDate == null");
		}

		public Builder(final ChargeVO charge) {

			Objects.requireNonNull(charge, "charge must not be null");

			setInvoice(charge.getInvoice()).setCreditCardVO(charge.getCreditCardVO()).setCurrency(charge.getCurrency())
					.setSumTotal(charge.getSumTotal()).setStatus(charge.getStatus())
					.setCreationDate(charge.getCreationDate());
		}

		public InvoiceVO getInvoice() {
			return invoice;
		}

		public Builder setInvoice(InvoiceVO invoice) {
			this.invoice = invoice;
			return this;
		}

		public CreditCardVO getCreditCardVO() {
			return creditCardVO;
		}

		public Builder setCreditCardVO(CreditCardVO creditCardVO) {
			this.creditCardVO = creditCardVO;
			return this;
		}

		public String getCurrency() {
			return currency;
		}

		public Builder setCurrency(String currency) {
			this.currency = currency;
			return this;
		}

		public BigDecimal getSumTotal() {
			return sumTotal;
		}

		public Builder setSumTotal(BigDecimal sumTotal) {
			this.sumTotal = sumTotal;
			return this;
		}

		public ChargeStatus getStatus() {
			return status;
		}

		public Builder setStatus(ChargeStatus status) {
			this.status = status;
			return this;
		}

		public DateTime getCreationDate() {
			return creationDate;
		}

		public Builder setCreationDate(DateTime creationDate) {
			this.creationDate = creationDate;
			return this;
		}

		@Override
		public ChargeVO build() {
			return new ChargeVO(this);
		}

	}

	private ChargeVO(Builder builder) {

		Objects.requireNonNull(builder, "builder must not be null");

		this.invoice = builder.getInvoice();
		this.creditCardVO = builder.getCreditCardVO();
		this.currency = builder.getCurrency();
		this.sumTotal = builder.getSumTotal();
		this.status = builder.getStatus();
		this.creationDate = builder.getCreationDate();
	}

	public InvoiceVO getInvoice() {
		return invoice;
	}

	public CreditCardVO getCreditCardVO() {
		return creditCardVO;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getSumTotal() {
		return sumTotal;
	}

	public ChargeStatus getStatus() {
		return status;
	}

	public DateTime getCreationDate() {
		return creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((creditCardVO == null) ? 0 : creditCardVO.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((sumTotal == null) ? 0 : sumTotal.hashCode());
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
		ChargeVO other = (ChargeVO) obj;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (creditCardVO == null) {
			if (other.creditCardVO != null)
				return false;
		} else if (!creditCardVO.equals(other.creditCardVO))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		if (status != other.status)
			return false;
		if (sumTotal == null) {
			if (other.sumTotal != null)
				return false;
		} else if (!sumTotal.equals(other.sumTotal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChargeVO [invoice=" + invoice + ", creditCardVO=" + creditCardVO + ", currency=" + currency
				+ ", sumTotal=" + sumTotal + ", status=" + status + ", creationDate=" + creationDate + "]";
	}

}
