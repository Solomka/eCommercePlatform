package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceItemVO;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceVO;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;
import ukma.eCommerce.core.userModule.validation.vo.CustomerVOValidator;
import ukma.eCommerce.core.userModule.validation.vo.SellerVOValidator;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

/**
 * <p>
 * Custom validator for {@link InvoiceDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
@Component
public final class InvoiceVOValidator implements Validator {

    private final SellerVOValidator sellerValidator;
    private final CustomerVOValidator customerValidator;
    private final OrderVOValidator orderValidator;
    private final InvoiceItemVOValidator invoiceItemValidator;

    @Autowired
    public InvoiceVOValidator(SellerVOValidator sellerValidator, CustomerVOValidator customerValidator,
                              OrderVOValidator orderValidator, InvoiceItemVOValidator invoiceItemValidator) {

        this.sellerValidator = Objects.requireNonNull(sellerValidator);
        this.customerValidator = Objects.requireNonNull(customerValidator);
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

        if (!sellerValidator.supports(Objects.requireNonNull(invoice.getSeller()).getClass()))
            throw new RuntimeException();

        if (!customerValidator.supports(Objects.requireNonNull(invoice.getCustomer()).getClass()))
            throw new RuntimeException();

        if (!orderValidator.supports(Objects.requireNonNull(invoice.getOrder()).getClass()))
            throw new RuntimeException();

        sellerValidator.validate(invoice.getSeller(), errors);
        customerValidator.validate(invoice.getCustomer(), errors);
        orderValidator.validate(invoice.getOrder(), errors);

        if (invoice.getInvoiceItems() == null || invoice.getInvoiceItems().isEmpty()) {
            errors.rejectValue("invoiceItems", "error.invoice.vo.invoiceItems",
                    "Invoice items weren't specified");
        } else {

            final Collection<InvoiceItemVO> items = invoice.getInvoiceItems();

            if (items.size() != invoice.getQuantityTotal()) {
                errors.reject("error.invoice.vo.quantity");
            } else {

                BigDecimal totalSum = BigDecimal.ZERO;

                for (final InvoiceItemVO item : items) {
                    totalSum = totalSum.add(item.getSumTotal());
                    invoiceItemValidator.validate(item, errors);
                }

                if (invoice.getSumTotal().compareTo(totalSum) != 0) {
                    errors.reject("error.invoice.vo.totalsum");
                }
            }
        }

        final DateTime creationDate = invoice.getCreationDate();
        final DateTime now = DateTime.now();

        if (creationDate == null) {
            errors.rejectValue("creationDate", "error.invoice.vo.creationDate",
                    "Creation date wasn't specified or broken");
        } else if (now.compareTo(creationDate) < 0) {
            errors.rejectValue("creationDate", "error.invoice.vo.creationDate",
                    String.format("Current time is recent than creation date, now is %s, but was - %s", now, creationDate));
        }

        if (invoice.getStatus() == null) {
            errors.rejectValue("status", "error.invoice.vo.status", "Status date wasn't specified");
        }
    }
}
