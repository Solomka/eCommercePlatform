package ukma.eCommerce.core.paymentModule.domainLogic.util;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * Factory which creates {@linkplain Charge} instance
 * </p>
 * Created by Максим on 11/3/2016.
 */
public interface IChargeFactory {

    /**
     * Creates instance of {@linkplain Charge} using given
     * instance of {@linkplain Invoice}
     *
     * @param invoice    invoice to build charge from
     * @param creditCard credit card which were used to conduct charge
     * @return instance of {@linkplain Charge}
     */
    @NotNull
    Charge create(@NotNull Invoice invoice, @NotNull CreditCard creditCard);

}
