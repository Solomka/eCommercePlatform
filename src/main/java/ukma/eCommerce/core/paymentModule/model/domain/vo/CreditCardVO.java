package ukma.eCommerce.core.paymentModule.model.domain.vo;

import org.joda.time.DateTime;

import java.util.Objects;

/**
 * <p>
 * Value object which describes credit card
 * </p>
 * 
 * @author Max
 */
public final class CreditCardVO {

	private final String number;
	private final String cvc;
	private final DateTime expirationDate;

	public CreditCardVO(String number, String cvv, DateTime expirationDate) {
		this.number = Objects.requireNonNull(number, "card number == null");
		this.cvc = Objects.requireNonNull(cvv, "cvc == null");
		this.expirationDate = Objects.requireNonNull(expirationDate, "exp date == null");
	}

	public String getNumber() {
		return number;
	}

	public String getCvv() {
		return cvc;
	}

	public DateTime getExpirationDate() {
		return expirationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cvc == null) ? 0 : cvc.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		CreditCardVO other = (CreditCardVO) obj;
		if (cvc == null) {
			if (other.cvc != null)
				return false;
		} else if (!cvc.equals(other.cvc))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CardForm [number=" + number + ", cvv=" + cvc + ", expirationDate=" + expirationDate + "]";
	}

}
