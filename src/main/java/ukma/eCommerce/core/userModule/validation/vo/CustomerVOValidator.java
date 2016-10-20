package ukma.eCommerce.core.userModule.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.util.validation.vo.FullNameVOValidator;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerVO;

import java.util.Objects;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class CustomerVOValidator implements Validator {

    private final UserVOValidator userValidator;
    private final FullNameVOValidator fullNameVOValidator;

    @Autowired
    public CustomerVOValidator(UserVOValidator userValidator, FullNameVOValidator fullNameVOValidator) {
        this.userValidator = Objects.requireNonNull(userValidator);
        this.fullNameVOValidator = fullNameVOValidator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.customer.vo.object", "Customer instance wasn't passed");
            return;
        }

        final CustomerVO customer = (CustomerVO) o;

        if (!userValidator.supports(customer.getClass()))
            throw new RuntimeException();

        if (!fullNameVOValidator.supports(Objects.requireNonNull(customer.getFullName()).getClass()))
            throw new RuntimeException();

        userValidator.validate(customer, errors);
        fullNameVOValidator.validate(customer.getFullName(), errors);



    }
}