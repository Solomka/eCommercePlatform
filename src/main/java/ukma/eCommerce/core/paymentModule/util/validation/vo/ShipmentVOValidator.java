package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ShipmentVO;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class ShipmentVOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ShipmentVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }

}
