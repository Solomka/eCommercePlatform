package ukma.eCommerce.core.userModule.validation.vo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.userModule.model.domain.vo.UserVO;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class UserVOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.user.vo.object", "User instance wasn't passed");
            return;
        }

        final UserVO user = (UserVO) o;


        /*if (!TextUtils.nullOrEmpty(user.getPhone()) && !PHONE_PATTERN_DELIM.matcher(user.getPhone()).matches()) {
            errors.rejectValue("email", "error.user.vo.phone", String.format("Phone '%s' is invalid", user.getPhone()));
        }*/
    }
}
