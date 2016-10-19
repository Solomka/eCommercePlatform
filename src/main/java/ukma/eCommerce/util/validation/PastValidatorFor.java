package ukma.eCommerce.util.validation;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;

/**
 * Created by Максим on 10/17/2016.
 */
public final class PastValidatorFor implements
        ConstraintValidator<Past, ReadableInstant> {

    public void initialize(Past constraintAnnotation) {
    }

    public boolean isValid(ReadableInstant value,
                           ConstraintValidatorContext constraintValidatorContext) {

        return value == null || value.isBefore(DateTime.now());
    }
}
