package ukma.eCommerce.core.paymentModule.util.validation.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;

/**
 * <p>
 * Custom validator for {@link OrderDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
public final class OrderDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        // TODO: 10/15/2016 add validation
    }
}
