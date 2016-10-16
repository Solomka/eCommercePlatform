package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ChargeVO;
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
        return ChargeVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.charge.vo.object", "Charge instance wasn't passed");
            return;
        }

        final ChargeVO chargeVO = (ChargeVO) o;

        invoiceValidator.validate(chargeVO.getInvoice(), errors);
        creditCardValidator.validate(chargeVO.getCreditCardVO(), errors);

        if (chargeVO.getStatus() == null) {
            errors.rejectValue("status", "error.charge.vo.status", "Status wasn't specified");
        }

        if (chargeVO.getCreationDate() == null) {
            errors.rejectValue("creationDate", "error.charge.vo.creationDate", "Creation date wasn't specified");
        } else if (DateTime.now().compareTo(chargeVO.getCreationDate()) < 0) {
            errors.rejectValue("creationDate", "error.charge.vo.creationDate", "Creation date > now");
        }
    }
}
