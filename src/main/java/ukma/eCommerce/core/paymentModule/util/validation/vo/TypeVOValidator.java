package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.TypeVO;
import ukma.eCommerce.util.TextUtils;

import java.util.Objects;
import java.util.regex.Pattern;

import static ukma.eCommerce.util.validation.ValidationUtil.PLAIN_STR;

/**
 * Created by Максим on 10/17/2016.
 */
@Component
public final class TypeVOValidator implements Validator {

    private static final Pattern TYPE_NAME_PATTERN = Pattern.compile(String.format(PLAIN_STR, 2, 128));

    private final CategoryVOValidator categoryVOValidator;

    @Autowired
    public TypeVOValidator(CategoryVOValidator categoryVOValidator) {
        this.categoryVOValidator = Objects.requireNonNull(categoryVOValidator);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TypeVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.type.vo.object", "Type instance wasn't passed");
            return;
        }

        final TypeVO type = (TypeVO) o;

        if (categoryVOValidator.supports(Objects.requireNonNull(type.getCategory()).getClass()))
            throw new RuntimeException();

        categoryVOValidator.validate(type.getCategory(), errors);

        if (type.getTypeId() < 0) {
            errors.rejectValue("typeId", "error.type.vo.typeId",
                    String.format("typeId '%d' is invalid", type.getTypeId()));
        }

        if (TextUtils.nullOrEmpty(type.getTypeName())
                || !TYPE_NAME_PATTERN.matcher(type.getTypeName()).matches()) {
            errors.rejectValue("typeName", "error.type.vo.typeName",
                    String.format("type Name '%s' is invalid", type.getTypeName()));
        }
    }
}
