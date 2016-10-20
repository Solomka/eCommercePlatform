package ukma.eCommerce.core.paymentModule.model.domain.vo;

import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Максим on 10/19/2016.
 */
public final class Money {

    @NotNull
    private final Currency currency;
    @NotNull
    @Min(0)
    private final BigDecimal amount;

    public Money(Currency currency, BigDecimal amount) {
        this.currency = Objects.requireNonNull(currency);
        this.amount = Objects.requireNonNull(amount);
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        if (currency != money.currency) return false;
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
        return "Money{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
