package ukma.eCommerce.core.paymentModule.repository.po;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;

@Embeddable
public class Price {

	public Price() {

	}

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "currency", nullable = false)
	@Enumerated(EnumType.STRING)
	private Currency currency;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCurrency() == null) ? 0 : getCurrency().hashCode());
		result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
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
		if (!(obj instanceof Price)) {
			return false;
		}
		Price other = (Price) obj;
		if (getCurrency() != other.getCurrency()) {
			return false;
		}
		if (getPrice() == null) {
			if (other.getPrice() != null) {
				return false;
			}
		} else if (!getPrice().equals(other.getPrice())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Price [price=" + price + ", currency=" + currency + "]";
	}

}
