package ukma.eCommerce.core.paymentModule.util.validation.dto;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO;

/**
 * <p>
 * Custom validator for {@link ukma.eCommerce.core.paymentModule.model.dwo.ChargeDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
@Component
public final class ChargeDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ChargeDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //TODO add validation
    }
}
