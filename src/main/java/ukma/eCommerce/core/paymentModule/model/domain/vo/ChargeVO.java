package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.util.validation.IValidateable;

import java.math.BigDecimal;

public final class ChargeVO implements IValidateable {

	public final InvoiceVO invoice;
	private CreditCardVO creditCardVO;
	private final BigDecimal sumTotal;
	private final ChargeStatus status;
	private final DateTime creationDate;

	public ChargeVO(InvoiceVO invoice, BigDecimal sumTotal, ChargeStatus status, DateTime creationDate) {
		super();
		this.invoice = invoice;
		this.sumTotal = sumTotal;
		this.status = status;
		this.creationDate = creationDate;
	}

	public InvoiceVO getInvoice() {
		return invoice;
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
	public String toString() {
		return "ChargeVO [invoice=" + invoice + ", sumTotal=" + sumTotal + ", status=" + status + ", creationDate="
				+ creationDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
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

}
