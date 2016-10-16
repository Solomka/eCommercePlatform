package ukma.eCommerce.core.userModule.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.userModule.model.domain.vo.CustomerVO;
import ukma.eCommerce.util.TextUtils;

import java.util.Objects;
import java.util.regex.Pattern;

import static ukma.eCommerce.util.validation.ValidationUtil.LOGIN_PATTERN;
import static ukma.eCommerce.util.validation.ValidationUtil.PASSWORD_PATTERN;
import static ukma.eCommerce.util.validation.ValidationUtil.PLAIN_STR;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class CustomerVOValidator implements Validator {

    private final UserVOValidator userValidator;

    private static final Pattern FIRST_NAME_PATTERN = Pattern.compile(String.format(PLAIN_STR, 3, 128));
    private static final Pattern LAST_NAME_PATTERN = Pattern.compile(String.format(PLAIN_STR, 3, 128));

    @Autowired
    public CustomerVOValidator(UserVOValidator userValidator) {
        this.userValidator = Objects.requireNonNull(userValidator);
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

        userValidator.validate(customer, errors);

        if (TextUtils.nullOrEmpty(customer.getFirstName())
                || !FIRST_NAME_PATTERN.matcher(customer.getFirstName()).matches()) {
            errors.rejectValue("firstName", "error.customer.vo.firstName",
                    String.format("First name '%s' is invalid", customer.getFirstName()));
        }

        if (TextUtils.nullOrEmpty(customer.getLastName())
                || !LAST_NAME_PATTERN.matcher(customer.getLastName()).matches()) {
            errors.rejectValue("lastName", "error.customer.vo.lastName",
                    String.format("Last name '%s' is invalid", customer.getLastName()));
        }

        if (TextUtils.nullOrEmpty(customer.getLogin())
                || !LOGIN_PATTERN.matcher(customer.getLogin()).matches()) {
            errors.rejectValue("login", "error.customer.vo.login",
                    String.format("Login '%s' is invalid", customer.getLogin()));
        }

        if (TextUtils.nullOrEmpty(customer.getPassword())
                || !PASSWORD_PATTERN.matcher(customer.getPassword()).matches()) {
            errors.rejectValue("password", "error.customer.vo.password",
                    String.format("Password '%s' is invalid", customer.getPassword()));
        }

    }
}
