package ukma.eCommerce.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Максим on 10/19/2016.
 */
public final class PasswordValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password phone) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || s.matches(ValidationUtil.PASSWORD_PATTERN);
    }
}
