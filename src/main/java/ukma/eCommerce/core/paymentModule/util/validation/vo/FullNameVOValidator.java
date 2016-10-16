package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.userModule.model.domain.vo.FullNameVO;
import ukma.eCommerce.util.TextUtils;

import java.util.regex.Pattern;

import static ukma.eCommerce.util.validation.ValidationUtil.PLAIN_STR;

/**
 * Created by Максим on 10/17/2016.
 */
@Component
public final class FullNameVOValidator implements Validator {

    private static final Pattern FIRST_NAME_PATTERN = Pattern.compile(String.format(PLAIN_STR, 3, 128));
    private static final Pattern LAST_NAME_PATTERN = Pattern.compile(String.format(PLAIN_STR, 3, 128));

    @Override
    public boolean supports(Class<?> aClass) {
        return FullNameVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.fullname.vo.object", "Full name instance wasn't passed");
            return;
        }

        final FullNameVO fullName = (FullNameVO) o;

        if (TextUtils.nullOrEmpty(fullName.getFirstName())
                || !FIRST_NAME_PATTERN.matcher(fullName.getFirstName()).matches()) {
            errors.rejectValue("firstName", "error.fullname.vo.firstName",
                    String.format("First name '%s' is invalid", fullName.getFirstName()));
        }

        if (TextUtils.nullOrEmpty(fullName.getLastName())
                || !LAST_NAME_PATTERN.matcher(fullName.getLastName()).matches()) {
            errors.rejectValue("lastName", "error.fullname.vo.lastName",
                    String.format("Last name '%s' is invalid", fullName.getLastName()));
        }

    }
}
