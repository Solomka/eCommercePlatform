package ukma.eCommerce.core.paymentModule.domainLogic.util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.stereotype.Component;

import ukma.eCommerce.core.paymentModule.model.domain.bo.Invoice;
import ukma.eCommerce.core.paymentModule.model.domain.vo.types.ChargeStatus;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeSaveDTO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoicePMDTO;

/**
 * Created by Максим on 11/3/2016.
 */
@Component
public final class ChargeFactory implements IChargeFactory {

	/**
	 * AR should always be consistent (id can not be NULL)
	 */
	@Override
	public ChargeSaveDTO create(@NotNull InvoicePMDTO invoice, @Null ChargeStatus status) {
		/*
		 * return new Charge.Builder().setId(null)
		 * .setPaymentDate(DateTime.now()) .setInvoice(invoice.getId())
		 * .setStatus(status == null ? ChargeStatus.SUCCEEDED : status)
		 * .build();
		 */
		return new ChargeSaveDTO.Builder().build();
	}

	@Override
	public ChargeSaveDTO create(@NotNull InvoicePMDTO invoice) {
		return create(invoice, null);
	}
}
