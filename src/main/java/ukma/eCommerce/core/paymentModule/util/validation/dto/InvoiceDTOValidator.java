package ukma.eCommerce.core.paymentModule.util.validation.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.dwo.InvoiceDTO;

/**
 * <p>
 * Custom validator for {@link InvoiceDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
@Component
public final class InvoiceDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return InvoiceDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // TODO: 10/15/2016 add validation
    }
}
