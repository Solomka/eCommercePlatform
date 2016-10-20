package ukma.eCommerce.util.validation;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Future;

/**
 * Created by Максим on 10/17/2016.
 */
public final class FutureValidator implements
        ConstraintValidator<Future, ReadableInstant> {

    public void initialize(Future constraintAnnotation) {
    }

    public boolean isValid(ReadableInstant value,
                           ConstraintValidatorContext constraintValidatorContext) {

        return value == null || value.isAfter(DateTime.now());
    }
}
