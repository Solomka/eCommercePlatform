package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItem;

import java.util.Objects;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class OrderItemVOValidator implements Validator {

    private final ProductVOValidator productValidator;

    @Autowired
    public OrderItemVOValidator(ProductVOValidator productValidator) {
        this.productValidator = Objects.requireNonNull(productValidator);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderItem.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.orderitem.vo.object", "Order item instance wasn't passed");
            return;
        }

        final OrderItem orderItem = (OrderItem) o;

        if (!productValidator.supports(Objects.requireNonNull(orderItem.getProduct()).getClass()))
            throw new RuntimeException();

        productValidator.validate(orderItem.getProduct(), errors);

        if (orderItem.getQuantity() < 1) {
            errors.reject("error.orderitem.vo.quantity",
                    String.format("Quantity less than one, was %d", orderItem.getQuantity()));
        }
    }

}
