package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.ProductVO;
import ukma.eCommerce.util.TextUtils;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.math.BigDecimal;
import java.util.regex.Pattern;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class ProductVOValidator implements Validator {

    private static final Pattern NAME_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 50));

    @Override
    public boolean supports(Class<?> aClass) {
        return ProductVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.product.vo.object", "Product item instance wasn't passed");
            return;
        }

        final ProductVO product = (ProductVO) o;

        if (TextUtils.nullOrEmpty(product.getName())
                || !NAME_PATTERN.matcher(product.getName()).matches()) {
            errors.rejectValue("name", "error.invoice.vo.name",
                    "Item name weren't specified or broken");
        }

        if (product.getPrice() == null) {
            errors.rejectValue("price", "error.invoice.vo.price",
                    "Item price weren't specified");
        } else if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("price", "error.invoice.vo.price",
                    String.format("Item price less then zero, was %s", product.getPrice()));
        }

        if(product.getQuantity() < 1) {
            errors.rejectValue("quantity", "error.invoice.vo.quantity",
                    String.format("Item quantity less then one, was %d", product.getQuantity()));
        }

        if(TextUtils.nullOrEmpty(product.getDescription()) || product.getDescription().length() > 50) {
            errors.rejectValue("description", "error.invoice.vo.description",
                    String.format("Invalid description, was %s", product.getDescription()));
        }
    }
}
