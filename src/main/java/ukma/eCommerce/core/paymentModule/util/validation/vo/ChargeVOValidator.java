package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Charge;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;

import java.util.Objects;

/**
 * <p>
 * Custom validator for {@link ChargeDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
@Component
public final class ChargeVOValidator implements Validator {

    private final InvoiceVOValidator invoiceValidator;
    private final CreditCardVOValidator creditCardValidator;

    @Autowired
    public ChargeVOValidator(InvoiceVOValidator invoiceValidator,
                             CreditCardVOValidator creditCardValidator) {

        this.invoiceValidator = Objects.requireNonNull(invoiceValidator);
        this.creditCardValidator = Objects.requireNonNull(creditCardValidator);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Charge.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.charge.vo.object", "Charge instance wasn't passed");
            return;
        }

        final Charge chargeVO = (Charge) o;

        invoiceValidator.validate(chargeVO.getInvoice(), errors);
        creditCardValidator.validate(chargeVO.getCreditCard(), errors);

        if (chargeVO.getStatus() == null) {
            errors.rejectValue("status", "error.charge.vo.status", "Status wasn't specified");
        }


    }
}
