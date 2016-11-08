package ukma.eCommerce.core.paymentModule.domainLogic.util;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeID;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Created by Максим on 11/3/2016.
 */
@Component
public final class ChargeFactory implements IChargeFactory {

    @Override
    public Charge create(@NotNull Invoice invoice, @Null ChargeStatus status) {
        return new Charge.Builder().setId(null)
                .setPaymentDate(DateTime.now())
                .setInvoice(invoice.getId())
                .setStatus(status == null ? ChargeStatus.SUCCEEDED : status)
                .build();
    }

    @Override
    public Charge create(@NotNull Invoice invoice) {
        return create(invoice, null);
    }
}
