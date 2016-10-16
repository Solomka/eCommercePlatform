package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public final class ChargeVO {

	private final InvoiceVO invoice;
	private final CreditCardVO creditCardVO;
	private final Currency currency;
	private final BigDecimal sumTotal;
	private final ChargeStatus status;
	private final DateTime creationDate;

	public ChargeVO(InvoiceVO invoice, CreditCardVO creditCardVO, Currency currency, BigDecimal sumTotal,
			ChargeStatus status, DateTime creationDate) {

		this.invoice = Objects.requireNonNull(invoice, "invoice == null");
		this.creditCardVO = Objects.requireNonNull(creditCardVO, "creditCard == null");
		this.currency = Objects.requireNonNull(currency, "currency == null");
		this.sumTotal = Objects.requireNonNull(sumTotal, "sumTotal == null");
		this.status = Objects.requireNonNull(status, "status == null");
		this.creationDate = Objects.requireNonNull(creationDate, "creationDate == null");
	}

	public InvoiceVO getInvoice() {
		return invoice;
	}

	public CreditCardVO getCreditCardVO() {
		return creditCardVO;
	}

	public Currency getCurrency() {
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
	public String toString() {
		return "ChargeVO [invoice=" + invoice + ", creditCardVO=" + creditCardVO + ", currency=" + currency
				+ ", sumTotal=" + sumTotal + ", status=" + status + ", creationDate=" + creationDate + "]";
	}

}
