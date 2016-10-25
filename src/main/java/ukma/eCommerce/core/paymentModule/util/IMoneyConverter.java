package ukma.eCommerce.core.paymentModule.util;

import org.springframework.validation.annotation.Validated;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Price;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.Currency;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by Максим on 10/20/2016.
 */
@Validated
public interface IMoneyConverter {

    @NotNull
    Price convert(@NotNull Currency currency, @NotNull BigDecimal amount);

    @NotNull
    Price convert(@NotNull Collection<Price> money, @NotNull Currency currency);

}
