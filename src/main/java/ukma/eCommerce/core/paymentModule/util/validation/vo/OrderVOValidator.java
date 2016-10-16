package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.OrderItemVO;
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
    private final ShipmentVOValidator shipmentVOValidator;
    private final OrderItemVOValidator orderItemVOValidator;

    @Autowired
    public OrderVOValidator(CustomerVOValidator customerValidator, SellerVOValidator sellerValidator,
                            ShipmentVOValidator shipmentVOValidator, OrderItemVOValidator orderItemVOValidator) {
        this.customerValidator = Objects.requireNonNull(customerValidator);
        this.sellerValidator = Objects.requireNonNull(sellerValidator);
        this.shipmentVOValidator = Objects.requireNonNull(shipmentVOValidator);
        this.orderItemVOValidator = Objects.requireNonNull(orderItemVOValidator);
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

        if (customerValidator.supports(Objects.requireNonNull(order.getCustomer()).getClass()))
            throw new RuntimeException();

        if (sellerValidator.supports(Objects.requireNonNull(order.getSeller()).getClass()))
            throw new RuntimeException();

        customerValidator.validate(order.getCustomer(), errors);
        sellerValidator.validate(order.getSeller(), errors);

        if(order.getShipment() != null) {

            if (shipmentVOValidator.supports(Objects.requireNonNull(order.getShipment()).getClass()))
                throw new RuntimeException();

            shipmentVOValidator.validate(order.getShipment(), errors);
        }

        if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            errors.rejectValue("orderItems", "error.order.vo.orderItems",
                    "Order items weren't specified");
        } else {
            for (final OrderItemVO item : order.getOrderItems()) {
                orderItemVOValidator.validate(item, errors);
            }
        }

        final DateTime creationDate = order.getCreationDate();
        final DateTime now = DateTime.now();

        if (creationDate == null) {
            errors.rejectValue("creationDate", "error.order.vo.creationDate",
                    "Creation date wasn't specified or broken");
        } else if (now.compareTo(creationDate) < 0) {
            errors.rejectValue("creationDate", "error.order.vo.creationDate",
                    String.format("Current time is recent than creation date, now is %s, but was - %s", now, creationDate));
        }

        if (order.getFulfilmentDate() != null && now.compareTo(order.getFulfilmentDate()) < 0) {
            errors.rejectValue("fulfilmentDate", "error.invoice.vo.fulfilmentDate",
                    String.format("fulfilmentDate date %s is later than current %s", order.getFulfilmentDate(), now));
        }

        if (order.getOrderStatus() == null) {
            errors.rejectValue("orderStatus", "error.invoice.vo.orderStatus", "Status wasn't specified");
        }
    }
}
