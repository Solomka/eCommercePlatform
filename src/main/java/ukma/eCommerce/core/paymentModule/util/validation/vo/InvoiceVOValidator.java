package ukma.eCommerce.core.paymentModule.util.validation.vo;

import java.math.BigDecimal;
import java.util.Objects;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceItemVO;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceVO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;

/**
 * <p>
 * Custom validator for {@link InvoiceDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
@Component
public final class InvoiceVOValidator implements Validator {

	private final OrderVOValidator orderValidator;
	private final InvoiceItemVOValidator invoiceItemValidator;

	@Autowired
	public InvoiceVOValidator(OrderVOValidator orderValidator, InvoiceItemVOValidator invoiceItemValidator) {

		this.orderValidator = Objects.requireNonNull(orderValidator);
		this.invoiceItemValidator = Objects.requireNonNull(invoiceItemValidator);
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return InvoiceVO.class.isAssignableFrom(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		if (o == null) {
			errors.reject("error.invoice.vo.object", "Invoice instance wasn't passed");
			return;
		}

		final InvoiceVO invoice = (InvoiceVO) o;

		if (!orderValidator.supports(Objects.requireNonNull(invoice.getOrder()).getClass()))
			throw new RuntimeException();

		orderValidator.validate(invoice.getOrder(), errors);

		if (invoice.getInvoiceItems() == null || invoice.getInvoiceItems().isEmpty()) {
			errors.rejectValue("invoiceItems", "error.invoice.vo.invoiceItems", "Invoice items weren't specified");
		} else {

			int quantity = 0;
			BigDecimal totalSum = BigDecimal.ZERO;

			for (final InvoiceItemVO item : invoice.getInvoiceItems()) {
				totalSum = totalSum.add(item.getSumTotal());
				quantity += item.getQuantity();
				invoiceItemValidator.validate(item, errors);
			}

			if (invoice.getSumTotal().compareTo(totalSum) != 0) {
				errors.rejectValue("sumTotal", "error.invoice.vo.sumTotal",
						String.format("Sum total was %s, expected %s", invoice.getSumTotal(), totalSum));
			}

			if (quantity != invoice.getQuantityTotal()) {
				errors.rejectValue("quantityTotal", "error.invoice.vo.quantityTotal",
						String.format("Quantity total was %d, expected %d", invoice.getQuantityTotal(), quantity));
			}
		}

		final DateTime creationDate = invoice.getCreationDate();
		final DateTime now = DateTime.now();

		if (creationDate == null) {
			errors.rejectValue("creationDate", "error.invoice.vo.creationDate",
					"Creation date wasn't specified or broken");
		} else if (now.compareTo(creationDate) < 0) {
			errors.rejectValue("creationDate", "error.invoice.vo.creationDate", String
					.format("Current time is recent than creation date, now is %s, but was - %s", now, creationDate));
		}
		/**
		 * payment date in ChargeVO
		 */

		/*
		 * 
		 * if (invoice.getPaymentDate() != null &&
		 * now.compareTo(invoice.getPaymentDate()) < 0) {
		 * errors.rejectValue("paymentDate", "error.invoice.vo.paymentDate",
		 * String.format("Payment date %s is later than current %s",
		 * invoice.getPaymentDate(), now)); }
		 */
		if (invoice.getStatus() == null) {
			errors.rejectValue("status", "error.invoice.vo.status", "Status date wasn't specified");
		}

		if (invoice.getCurrency() == null) {
			errors.rejectValue("currency", "error.invoice.vo.currency", "Currency wasn't specified");
		}
	}
}
