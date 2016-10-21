package ukma.eCommerce.core.paymentModule.repository.po;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;

@Embeddable
public class Money {
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

}
