package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.CategoryVO;
import ukma.eCommerce.util.TextUtils;

import java.util.regex.Pattern;

import static ukma.eCommerce.util.validation.ValidationUtil.PLAIN_STR;

/**
 * Created by Максим on 10/17/2016.
 */
@Component
public final class CategoryVOValidator implements Validator {

    private static final Pattern CATEGORY_NAME_PATTERN = Pattern.compile(String.format(PLAIN_STR, 2, 128));

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.category.vo.object", "Category instance wasn't passed");
            return;
        }

        final CategoryVO category = (CategoryVO) o;

        if (category.getId() < 0) {
            errors.rejectValue("id", "error.category.vo.id",
                    String.format("id '%d' is invalid", category.getId()));
        }

        if (TextUtils.nullOrEmpty(category.getName())
                || !CATEGORY_NAME_PATTERN.matcher(category.getName()).matches()) {
            errors.rejectValue("name", "error.category.vo.name",
                    String.format("Name '%s' is invalid", category.getName()));
        }
    }
}
