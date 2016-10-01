package ukma.eCommerce.controller.vo;

import java.util.Objects;

import org.joda.time.DateTime;

/**
 * <p>
 * Value object which describes credit card form
 * </p>
 * @author ������
 * */
public final class CardForm {
	
	private final String number;
	private final String cvv;
	private final DateTime expirationDate;
	
	public CardForm(String number, String cvv, DateTime expirationDate) {
		this.number = Objects.requireNonNull(number, "card number == null");
		this.cvv = Objects.requireNonNull(cvv, "cvv == null");
		this.expirationDate = Objects.requireNonNull(expirationDate, "exp date == null");
	}

	public String getNumber() {
		return number;
	}

	public String getCvv() {
		return cvv;
	}

	public DateTime getExpirationDate() {
		return expirationDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cvv == null) ? 0 : cvv.hashCode());
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
		CardForm other = (CardForm) obj;
		if (cvv == null) {
			if (other.cvv != null)
				return false;
		} else if (!cvv.equals(other.cvv))
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
		return "CardForm [number=" + number + ", cvv=" + cvv + ", expirationDate=" + expirationDate + "]";
	}

}
