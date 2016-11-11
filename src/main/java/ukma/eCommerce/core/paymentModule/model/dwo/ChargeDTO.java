package ukma.eCommerce.core.paymentModule.model.dwo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

/**
 * 
 * @author Solomka
 *
 */
public final class ChargeDTO {

	@NotEmpty
	private String InvoiceId;
	@NotNull
	private DateTime paymentDate;
	
	public ChargeDTO() {
		
	}

	public String getInvoiceId() {
		return InvoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		InvoiceId = invoiceId;
	}

	public DateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(DateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "ChargeDTO [InvoiceId=" + InvoiceId + ", paymentDate=" + paymentDate + "]";
	}
	
	

	
}
