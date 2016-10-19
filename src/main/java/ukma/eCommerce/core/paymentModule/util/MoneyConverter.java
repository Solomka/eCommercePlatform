package ukma.eCommerce.core.paymentModule.util;

import org.springframework.stereotype.Service;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Money;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by Максим on 10/19/2016.
 */
@Service
public final class MoneyConverter implements IMoneyConverter {

    @Override
    public Money convert(Currency currency, BigDecimal amount) {
        // todo remove stub
        return new Money(currency, amount);
    }

    @Override
    public Money convert(Collection<Money> money, Currency currency) {
// todo remove stub
        BigDecimal amount = BigDecimal.ZERO;

        for (Money mMoney : money) {
            amount = amount.add(mMoney.getAmount());
        }

        return new Money(currency, amount);
    }

}
