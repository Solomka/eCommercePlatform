package ukma.eCommerce.core.paymentModule.util;

import org.springframework.stereotype.Component;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Money;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by Максим on 10/19/2016.
 */
@Component
// todo extract interface
public final class MoneyConverter {

    public Money convert(Currency currency, BigDecimal amount) {
        // todo finish
        return new Money(currency, amount);
    }

    public Money convert(Collection<Money> money, Currency currency) {

        BigDecimal amount = BigDecimal.ZERO;

        for (Money mMoney : money) {
            amount = amount.add(mMoney.getAmount());
        }

        return new Money(currency, amount);
    }

}
