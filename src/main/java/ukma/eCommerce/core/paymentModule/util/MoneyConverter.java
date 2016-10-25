package ukma.eCommerce.core.paymentModule.util;

import org.springframework.stereotype.Service;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by Максим on 10/19/2016.
 */
@Service
public final class MoneyConverter implements IMoneyConverter {

    @Override
    public Price convert(Currency currency, BigDecimal amount) {
        // todo remove stub
        return new Price(currency, amount);
    }

    @Override
    public Price convert(Collection<Price> money, Currency currency) {
// todo remove stub
        BigDecimal amount = BigDecimal.ZERO;

        for (Price mMoney : money) {
            amount = amount.add(mMoney.getAmount());
        }

        return new Price(currency, amount);
    }

}
