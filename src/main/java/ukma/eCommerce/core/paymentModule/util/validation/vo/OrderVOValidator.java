package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderVO;
import ukma.eCommerce.core.paymentModule.model.dwo.OrderDTO;
import ukma.eCommerce.core.userModule.validation.vo.CustomerVOValidator;
import ukma.eCommerce.core.userModule.validation.vo.SellerVOValidator;

import java.util.Objects;

/**
 * <p>
 * Custom validator for {@link OrderDTO}
 * </p>
 * Created by Максим on 10/15/2016.
 */
@Component
public final class OrderVOValidator implements Validator {

    private final CustomerVOValidator customerValidator;
    private final SellerVOValidator sellerValidator;

    @Autowired
    public OrderVOValidator(CustomerVOValidator customerValidator, SellerVOValidator sellerValidator) {
        this.customerValidator = Objects.requireNonNull(customerValidator);
        this.sellerValidator = Objects.requireNonNull(sellerValidator);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderVO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.order.vo.object", "Order instance wasn't passed");
            return;
        }

        final OrderVO order = (OrderVO) o;


        /*
        *
        * private final CustomerVO customer;
	private final SellerVO seller;
	private final ShipmentVO shipment;
	private final List<OrderItemVO> orderItems;
	private final OrderStatus orderStatus;
	private final DateTime creationDate;
	private final DateTime fulfilmentDate;
        * */
    }
}
