package ukma.eCommerce.core.paymentModule.util.validation.vo;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ukma.eCommerce.core.paymentModule.model.domain.vo.Shipment;
import ukma.eCommerce.util.validation.ValidationUtil;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created by Максим on 10/16/2016.
 */
@Component
public final class ShipmentVOValidator implements Validator {

    private final Pattern SERVICE_PATTERN = Pattern.compile(String.format(ValidationUtil.PLAIN_STR, 3, 128));

    private final AddressVOValidator addressVOValidator;

    @Autowired
    public ShipmentVOValidator(AddressVOValidator addressVOValidator) {
        this.addressVOValidator = Objects.requireNonNull(addressVOValidator);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Shipment.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        if (o == null) {
            errors.reject("error.shipment.vo.object", "Shipment instance wasn't passed");
            return;
        }

        final Shipment shipment = (Shipment) o;

        if (addressVOValidator.supports(Objects.requireNonNull(shipment.getAddress()).getClass()))
            throw new RuntimeException();

        addressVOValidator.validate(shipment.getAddress(), errors);

        if (shipment.getDeliveryService() != null &&
                !SERVICE_PATTERN.matcher(shipment.getDeliveryService()).matches()) {
            errors.rejectValue("deliveryService", "error.shipment.vo.deliveryService",
                    "deliveryService isn't valid");
        }

        final DateTime now = DateTime.now();

        if (shipment.getDeliveryDate() == null) {
            errors.rejectValue("deliveryDate", "error.shipment.vo.deliveryDate",
                    "deliveryDate wasn't specified");
        } else if (now.compareTo(shipment.getDeliveryDate()) > 0) {
            errors.rejectValue("deliveryDate", "error.shipment.vo.deliveryDate",
                    "deliveryDate isn't valid");
        }

        if (shipment.getStatus() == null) {
            errors.rejectValue("status", "error.shipment.vo.status",
                    "status wasn't specified");
        }

        if (shipment.getTotalSum() == null || shipment.getTotalSum().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("totalSum", "error.shipment.vo.totalSum",
                    "totalSum wasn't specified or isn't valid");
        }
    }

}
