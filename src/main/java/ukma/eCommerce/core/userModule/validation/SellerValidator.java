package ukma.eCommerce.core.userModule.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.userModule.model.domain.bo.Seller;
import ukma.eCommerce.util.TextUtils;

import static ukma.eCommerce.core.userModule.validation.UserValidator.PLAIN_STR_PATTERN;

/**
 * Created by Максим on 10/15/2016.
 */
@Component
public final class SellerValidator implements Validator {

    private final UserValidator validator;

    @Autowired
    public SellerValidator(UserValidator validator) {
        this.validator = validator;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Seller.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        final Seller seller = (Seller) o;

        ValidationUtils.invokeValidator(validator, seller, errors);

        if (!TextUtils.nullOrEmpty(seller.getCompany()) && !PLAIN_STR_PATTERN.matcher(seller.getCompany()).matches()) {
            errors.rejectValue("company", "error.user.company", String.format("Company name '%s' is invalid", seller.getCompany()));
        }
    }
}
