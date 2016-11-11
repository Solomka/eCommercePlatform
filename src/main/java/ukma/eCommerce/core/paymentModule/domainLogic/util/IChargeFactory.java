package ukma.eCommerce.core.paymentModule.domainLogic.util;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoicePMDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
     * @param invoice invoice to build charge from
     * @param status  charge status to be set. If null, then default {@link ChargeStatus#SUCCEEDED}
     *                will be set
     * @return instance of {@linkplain Charge}
     */
    @NotNull
    ChargeSaveDTO create(@NotNull InvoicePMDTO invoice, @Null ChargeStatus status);

    /**
     * Just a shortcut for {@link #create(Invoice, ChargeStatus)}. In this case default status will be set
     *
     * @param invoice invoice to build charge from
     * @return instance of {@linkplain Charge}
     */
    @NotNull
    ChargeSaveDTO create(@NotNull InvoicePMDTO invoice);

}
