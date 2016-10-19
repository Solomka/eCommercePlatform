package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.bo.Product;
import ukma.eCommerce.core.userModule.validation.vo.SellerVOValidator;
import ukma.eCommerce.util.TextUtils;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class ProductVOValidator implements Validator {

    private static final Pattern NAME_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 50));

    private final SellerVOValidator sellerVOValidator;
    private final TypeVOValidator typeVOValidator;

    @Autowired
    public ProductVOValidator(SellerVOValidator sellerVOValidator, TypeVOValidator typeVOValidator) {
        this.sellerVOValidator = Objects.requireNonNull(sellerVOValidator);
        this.typeVOValidator = Objects.requireNonNull(typeVOValidator);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Product.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.product.vo.object", "Product item instance wasn't passed");
            return;
        }

        final Product product = (Product) o;

        if(sellerVOValidator.supports(Objects.requireNonNull(product.getSeller()).getClass()))
            throw new RuntimeException();

        if(typeVOValidator.supports(Objects.requireNonNull(product.getType()).getClass()))
            throw new RuntimeException();

        sellerVOValidator.validate(product.getSeller(), errors);
        typeVOValidator.validate(product.getType(), errors);

        if (TextUtils.nullOrEmpty(product.getName())
                || !NAME_PATTERN.matcher(product.getName()).matches()) {
            errors.rejectValue("name", "error.product.vo.name",
                    "Item name weren't specified or broken");
        }

        if (product.getPrice() == null) {
            errors.rejectValue("price", "error.product.vo.price",
                    "Item price weren't specified");
        } else if (product.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("price", "error.product.vo.price",
                    String.format("Item price less then zero, was %s", product.getPrice()));
        }

        if(product.getAvailableQuantity() < 1) {
            errors.rejectValue("quantity", "error.product.vo.quantity",
                    String.format("Item quantity less then one, was %d", product.getAvailableQuantity()));
        }

        if(TextUtils.nullOrEmpty(product.getDescription()) || product.getDescription().length() > 50) {
            errors.rejectValue("description", "error.product.vo.description",
                    String.format("Invalid description, was %s", product.getDescription()));
        }

        if (product.getCurrency() == null) {
            errors.rejectValue("currency", "error.product.vo.currency", "Currency wasn't specified");
        }
    }
}
