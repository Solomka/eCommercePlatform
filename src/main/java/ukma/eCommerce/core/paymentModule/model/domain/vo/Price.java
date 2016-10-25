package ukma.eCommerce.core.paymentModule.model.domain.vo;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;
import ukma.eCommerce.util.validation.ValidationUtil;

/**
 * Created by Максим on 10/19/2016.
 */
public final class Price {

	@NotNull
	private final Currency currency;
	@NotNull
	@Min(0)
	private final BigDecimal amount;

	public Price(Currency currency, BigDecimal amount) {

		this.currency = ValidationUtil.validate(currency);
		this.amount = ValidationUtil.validate(amount);

		/*
		 * if (amount.compareTo(BigDecimal.ZERO) < 0) { throw new
		 * IllegalArgumentException("Amount of money should be not negative"); }
		 */

	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Price money = (Price) o;

		if (currency != money.currency)
			return false;
		return amount.equals(money.amount);

	}

	@Override
	public int hashCode() {
		int result = currency.hashCode();
		result = 31 * result + amount.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Price{" + "currency=" + currency + ", amount=" + amount + '}';
	}
}
