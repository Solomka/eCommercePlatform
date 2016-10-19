package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.InvoiceItem;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class InvoiceItemVOValidator implements Validator {

    private final OrderVOValidator orderValidator;
    private final ProductVOValidator productValidator;

    @Autowired
    public InvoiceItemVOValidator(OrderVOValidator orderValidator, ProductVOValidator productValidator) {
        this.orderValidator = Objects.requireNonNull(orderValidator);
        this.productValidator = Objects.requireNonNull(productValidator);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return InvoiceItem.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.invoiceitem.vo.object", "Invoice item instance wasn't passed");
            return;
        }

        final InvoiceItem invoiceItem = (InvoiceItem) o;

        if (!orderValidator.supports(Objects.requireNonNull(invoiceItem.getOrder()).getClass())) {
            throw new RuntimeException();
        }

        if (!productValidator.supports(Objects.requireNonNull(invoiceItem.getProduct()).getClass())) {
            throw new RuntimeException();
        }

        orderValidator.validate(invoiceItem.getOrder(), errors);
        productValidator.validate(invoiceItem.getProduct(), errors);

        if (invoiceItem.getQuantity() < 1) {
            // quantity should be greater or equals 1
            errors.rejectValue("quantity", "error.invoiceitem.vo.quantity",
                    String.format("Item quantity less then one, was %d", invoiceItem.getQuantity()));
        } else if (invoiceItem.getQuantity() > invoiceItem.getProduct().getQuantity()) {
            // quantity should less than available quantity
            errors.rejectValue("quantity", "error.invoiceitem.vo.quantity",
                    String.format("Item quantity %s is more than product quantity %s ",
                            invoiceItem.getQuantity(), invoiceItem.getProduct().getQuantity()));
        }

        if (invoiceItem.getSumTotal() == null) {
            errors.rejectValue("sumTotal", "error.invoiceitem.vo.sumTotal", "sumTotal less then zero, wasn't specified");
        } else if (BigDecimal.ZERO.compareTo(invoiceItem.getSumTotal()) > 0) {
            errors.rejectValue("sumTotal", "error.invoiceitem.vo.sumTotal",
                    String.format("sumTotal less then zero, was %s", invoiceItem.getSumTotal()));
        }
    }
}
