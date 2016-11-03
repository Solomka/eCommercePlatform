package ukma.eCommerce.core.paymentModule.domainLogic.util;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CreditCard;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by Максим on 11/3/2016.
 */
@Component
public final class ChargeFactory implements IChargeFactory {

    @Override
    public Charge create(@NotNull Invoice invoice, @NotNull CreditCard creditCard) {
        return new Charge.Builder().setId(new ChargeID())
                .setPaymentDate(DateTime.now())
                .setInvoice(invoice.getId())
                .setStatus(ChargeStatus.SUCCEEDED)
                .setCreditCard(creditCard).build();
    }
}
